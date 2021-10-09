package dev.liambloom.util.function;

import java.util.function.LongUnaryOperator;

@Unchecked(LongUnaryOperator.class)
@FunctionalInterface
public interface LongUnaryOperatorThrowsException {
    long applyAsLong(long operand) throws Exception;

    default LongUnaryOperatorThrowsException compose(LongUnaryOperatorThrowsException before) {
        return operand -> applyAsLong(before.applyAsLong(operand));
    }

    default LongUnaryOperatorThrowsException andThen(LongUnaryOperatorThrowsException after) {
        return operand -> after.applyAsLong(applyAsLong(operand));
    }

    static LongUnaryOperatorThrowsException indentity() {
        return operand -> operand;
    }
}
