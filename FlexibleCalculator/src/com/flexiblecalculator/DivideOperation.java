package com.flexiblecalculator;

public class DivideOperation implements OperationStrategy {
    @Override
    public Number execute(Number num1, Number num2) {
        if (num2.doubleValue() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return num1.doubleValue() / num2.doubleValue(); // Using double for simplicity
    }
}
