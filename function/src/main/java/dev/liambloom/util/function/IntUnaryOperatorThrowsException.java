package dev.liambloom.util.function;

import java.util.function.IntUnaryOperator;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(IntUnaryOperator.class)
@FunctionalInterface
public interface IntUnaryOperatorThrowsException {
    int applyAsInt(int operand) throws Exception;

    default IntUnaryOperatorThrowsException compose(IntUnaryOperatorThrowsException before) {
        return operand -> applyAsInt(before.applyAsInt(operand));
    }

    default IntUnaryOperatorThrowsException andThen(IntUnaryOperatorThrowsException after) {
        return operand -> after.applyAsInt(applyAsInt(operand));
    }

    static IntUnaryOperatorThrowsException indentity() {
        return operand -> operand;
    }
}
