package com.flexiblecalculator;

import java.util.Map;
import java.util.List;

public class Calculator {
    private Map<Operation, OperationStrategy> operationMap;

    // Constructor where operations are injected
    public Calculator(Map<Operation, OperationStrategy> operationMap) {
        this.operationMap = operationMap;
    }

    public Number calculate(Operation op, Number num1, Number num2) {
    	OperationStrategy strategy = operationMap.get(op);
        if (strategy == null) {
            throw new UnsupportedOperationException("Operation not supported");
        }
        return strategy.execute(num1, num2);
    	
    }

    // Example of chaining
    public Number chainOperations(Number initial, List<Pair<Operation, Number>> operations) {
        Number result = initial;
        for (Pair<Operation, Number> op : operations) {
            result = calculate(op.getFirst(), result, op.getSecond());
            
        }
        return result;
    }
}
