package dev.liambloom.util.annotation;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SupportedAnnotationTypes("dev.liambloom.util.annotation.Unchecked")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class FunctionUtilsAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

            if (annotatedElements.isEmpty())
                continue;
            try (PrintWriter out =
                    new PrintWriter(processingEnv.getFiler()
                        .createSourceFile("dev.liambloom.util.function.FunctionUtils", annotatedElements.toArray(new Element[0]))
                        .openWriter())) {
                out.println("package dev.liambloom.util.function;");
                out.println();
                out.println("public final class FunctionUtils {");
                out.println("\tprivate FunctionUtils() {}");
                out.println();
                out.println("\tprivate static <E extends Exception> void throwException(Exception e) throws E {");
                out.println("\t\tthrow (E) e;");
                out.println("\t}");
                out.println();

                for (Element elementNotCast : annotatedElements) {
                    TypeElement element = (TypeElement) elementNotCast;
                    AnnotationMirror annotationMirror = element.getAnnotationMirrors()
                        .stream()
                        .filter(a -> a.getAnnotationType().asElement().equals(annotation))
                        .findAny()
                        .orElse(null);

                    if (annotationMirror == null) {
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Unable to get @Unchecked annotation mirror", element);
                        continue;
                    }

                    if (element.getAnnotation(FunctionalInterface.class) == null) {
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "@Unchecked can only be applied to functional interfaces", element, annotationMirror);
                        continue;
                    }

                    ExecutableElement valueMethod = annotation.getEnclosedElements().stream()
                        .filter(ExecutableElement.class::isInstance)
                        .map(ExecutableElement.class::cast)
                        .filter(sub -> sub.getSimpleName().contentEquals("value"))
                        .filter(sub -> sub.getParameters().isEmpty())
                        .findAny()
                        .orElse(null);

                    if (valueMethod == null) {
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Unable to get value() method in @Unchecked", element);
                        continue;
                    }

                    AnnotationValue uncheckedAnnotationValue = annotationMirror.getElementValues().get(valueMethod);
                    TypeMirror uncheckedMirror = (TypeMirror) uncheckedAnnotationValue.getValue();
                    TypeElement uncheckedElement = (TypeElement) processingEnv.getTypeUtils().asElement(uncheckedMirror);

                    if (uncheckedElement.getAnnotation(FunctionalInterface.class) == null){
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "The value of @Unchecked must be a functional interface",
                            element, annotationMirror, uncheckedAnnotationValue);
                        continue;
                    }

                    List<? extends TypeParameterElement> elementParams = element.getTypeParameters();
                    List<? extends TypeParameterElement> uncheckedElementParams = uncheckedElement.getTypeParameters();
                    if (elementParams.size() != uncheckedElementParams.size()) {
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "The value of @Unchecked must have the same number of type parameters as the annotated type",
                            element, annotationMirror, uncheckedAnnotationValue);
                        continue;
                    }
                    if (elementParams.stream()
                        .map(TypeParameterElement::getBounds)
                        .flatMap(List::stream)
                        .map(processingEnv.getTypeUtils()::asElement)
                        .anyMatch(b -> !(processingEnv.getElementUtils().getPackageOf(b).getQualifiedName().contentEquals("java.lang")
                            && b.getSimpleName().contentEquals("Object")))) {
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "@Unchecked does not currently support bound type parameters, bound type parameter found on @Unchecked target",
                            element, annotationMirror);
                    }
                    if (uncheckedElementParams.stream()
                        .map(TypeParameterElement::getBounds)
                        .flatMap(List::stream)
                        .map(processingEnv.getTypeUtils()::asElement)
                        .anyMatch(b -> !(processingEnv.getElementUtils().getPackageOf(b).getQualifiedName().contentEquals("java.lang")
                            && b.getSimpleName().contentEquals("Object")))) {
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "@Unchecked does not currently support bound type parameters, bound type parameter found on @Unchecked value",
                            element, annotationMirror, uncheckedAnnotationValue);
                    }

                    ExecutableElement abstractMethod = null;
                    Set<ExecutableElement> implementedMethods = new HashSet<>();
                    TypeMirror elementOrSuperinterface = element.asType();

                    while (elementOrSuperinterface != null) {
                        Map<Boolean, Set<ExecutableElement>> instanceMethods = processingEnv.getTypeUtils().asElement(elementOrSuperinterface)
                            .getEnclosedElements()
                            .stream()
                            .filter(ExecutableElement.class::isInstance)
                            .map(ExecutableElement.class::cast)
                            .filter(e -> !e.getModifiers().contains(Modifier.STATIC))
                            .collect(Collectors.partitioningBy(ExecutableElement::isDefault, Collectors.toSet()));
                        Set<ExecutableElement> abstractMethods = instanceMethods.get(false);

                        abstractMethods
                            .removeIf(method -> implementedMethods.stream().anyMatch(subMethod ->
                                processingEnv.getElementUtils().overrides(subMethod, method, (TypeElement) subMethod.getEnclosingElement())));

                        if (abstractMethods.size() == 1) {
                            abstractMethod = abstractMethods.iterator().next();
                            break;
                        }
                        assert abstractMethods.isEmpty();

                        implementedMethods.addAll(instanceMethods.get(true));

                        List<? extends TypeMirror> superInterfaceList = ((TypeElement) processingEnv.getTypeUtils().asElement(elementOrSuperinterface)).getInterfaces();
                        elementOrSuperinterface = superInterfaceList.isEmpty() ? null : superInterfaceList.get(0);
                    }
                    assert abstractMethod != null;

                    String typeParametersUndelemitedString =
                        element.getTypeParameters().stream().map(TypeParameterElement::getSimpleName).collect(Collectors.joining(", "));
                    String typeParametersString = typeParametersUndelemitedString.isEmpty() ? "" : '<'
                        + typeParametersUndelemitedString + '>';

                    String paramsString = '('
                        + IntStream.range(0, abstractMethod.getParameters().size()).mapToObj(i -> "p" + i).collect(Collectors.joining(", ")) + ')';

                    boolean returns = !processingEnv.getTypeUtils().isSameType(processingEnv.getTypeUtils().getNoType(TypeKind.VOID),  abstractMethod.getReturnType());

                    String defaultReturnString;
                    if (processingEnv.getTypeUtils().isAssignable(processingEnv.getTypeUtils().getNullType(), abstractMethod.getReturnType()))
                        defaultReturnString = "null";
                    else if (processingEnv.getTypeUtils().isSameType(processingEnv.getTypeUtils().getPrimitiveType(TypeKind.BOOLEAN), abstractMethod.getReturnType()))
                        defaultReturnString = "false";
                    else
                        defaultReturnString = "0";

                    out.print("\tpublic static ");
                    out.print(typeParametersString);
                    out.print(' ');
                    out.print(processingEnv.getElementUtils().getPackageOf(uncheckedElement).getQualifiedName());
                    out.print('.');
                    out.print(uncheckedElement.getSimpleName());
                    out.print(typeParametersString);
                    out.print(" unchecked(");
                    out.print(element.getSimpleName());
                    out.print(typeParametersString);
                    out.print(' ');
                    out.println("checked) {");
                    out.print("\t\treturn ");
                    out.print(paramsString);
                    out.println(" -> {");
                    out.println("\t\t\ttry {");
                    out.print("\t\t\t\t");
                    if (returns)
                        out.print("return ");
                    out.print("checked.");
                    out.print(abstractMethod.getSimpleName());
                    out.print(paramsString);
                    out.println(';');
                    out.println("\t\t\t}");
                    out.println("\t\t\tcatch (Exception e) {");
                    out.println("\t\t\t\tthrowException(e);");
                    if (returns) {
                        out.print("\t\t\t\treturn ");
                        out.print(defaultReturnString);
                        out.println(';');
                    }
                    out.println("\t\t\t}");
                    out.println("\t\t};");
                    out.println("\t}");
                    out.println();
                }

                out.println('}');
            }
            catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
            }
        }

        return true;
    }
}
