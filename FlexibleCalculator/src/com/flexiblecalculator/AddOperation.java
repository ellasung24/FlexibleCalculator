package com.flexiblecalculator;

public class AddOperation implements OperationStrategy {
    @Override
    public Number execute(Number num1, Number num2) {
        return num1.doubleValue() + num2.doubleValue(); // Using double for simplicity
    }
}
