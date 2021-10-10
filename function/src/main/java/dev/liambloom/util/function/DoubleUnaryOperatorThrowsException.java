package dev.liambloom.util.function;

import java.util.function.DoubleUnaryOperator;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(DoubleUnaryOperator.class)
@FunctionalInterface
public interface DoubleUnaryOperatorThrowsException {
    double applyAsDouble(double operand) throws Exception;

    default DoubleUnaryOperatorThrowsException compose(DoubleUnaryOperatorThrowsException before) {
        return operand -> applyAsDouble(before.applyAsDouble(operand));
    }

    default DoubleUnaryOperatorThrowsException andThen(DoubleUnaryOperatorThrowsException after) {
        return operand -> after.applyAsDouble(applyAsDouble(operand));
    }

    static DoubleUnaryOperatorThrowsException indentity() {
        return operand -> operand;
    }
}
