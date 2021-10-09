package dev.liambloom.util.function;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SupportedAnnotationTypes("dev.liambloom.util.function.Unchecked")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
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
                        .anyMatch(b -> processingEnv.getElementUtils().getPackageOf(b).getQualifiedName().contentEquals("java.lang")
                            && b.getSimpleName().contentEquals("Object"))) {
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "@Unchecked does not currently support bound type parameters, bound type parameter found on @Unchecked target",
                            element, annotationMirror);
                    }
                    if (uncheckedElementParams.stream()
                        .map(TypeParameterElement::getBounds)
                        .anyMatch(((Predicate<List<? extends TypeMirror>>) Collection::isEmpty).negate())) {
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "@Unchecked does not currently support bound type parameters, bound type parameter found on @Unchecked value",
                            element, annotationMirror, uncheckedAnnotationValue);
                    }

                    String typeParametersString = '<'
                        + element.getTypeParameters().stream().map(TypeParameterElement::getSimpleName).collect(Collectors.joining(", ")) + '>';
                    String paramsString = '('
                        + IntStream.range(0, elementParams.size()).mapToObj(i -> "p" + i).collect(Collectors.joining(", ")) + ')';

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
                    out.print("\t\t\t\treturn checked.");
                    // TODO: I need to get the name of the method
                    out.print(paramsString);
                    out.println(';');
                    out.println("\t\t\t}");
                    out.println("\t\t\tcatch (Exception e) {");
                    out.println("\t\t\t\tthrowException(e);");
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
