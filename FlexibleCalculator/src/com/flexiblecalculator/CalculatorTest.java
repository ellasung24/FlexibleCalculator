package com.flexiblecalculator;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CalculatorTest {
	
	private static final Logger logger = Logger.getLogger(CalculatorTest.class.getName());
	
    @Test
    public void testAddition() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.ADD, new AddOperation());
        Calculator calculator = new Calculator(operationMap);
        double result = ((Number) calculator.calculate(Operation.ADD, 2, 3)).doubleValue();  // Cast to double
        logger.info("Addition: 2 + 3 = " + result);
        assertEquals(5.0, result, 0.00000001);
    }
    
    @Test
    public void testSubtraction() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.SUBTRACT, new SubtractOperation());
        Calculator calculator = new Calculator(operationMap);
        double result = ((Number) calculator.calculate(Operation.SUBTRACT, 2, 3)).doubleValue();  // Cast to double
        logger.info("Subtraction: 2 - 3 = " + result);
        assertEquals(-1.0, result, 0.00000001);
    }
    
    @Test
    public void testMultiplication() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.MULTIPLY, new MultiplyOperation());
        Calculator calculator = new Calculator(operationMap);
        double result = ((Number) calculator.calculate(Operation.MULTIPLY, 2, 3)).doubleValue();  // Cast to double
        logger.info("Multiplication: 2 * 3 = " + result);
        assertEquals(6.0, result, 0.00000001);
    }
    
    @Test
    public void testDivision() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.DIVIDE, new DivideOperation());
        Calculator calculator = new Calculator(operationMap);
        double result = ((Number) calculator.calculate(Operation.DIVIDE, 3, 7)).doubleValue();  // Cast to double
        logger.info("Division: 3 / 7 = " + result);
        assertEquals(0.42857142, result, 0.00000001);
    }
    
    
 // Test Division by Zero with error message capture
    @Test
    public void testDivisionByZero() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.DIVIDE, new DivideOperation());
        Calculator calculator = new Calculator(operationMap);
        logger.info("Testing Divide by Zero.");

        try {
        	calculator.calculate(Operation.DIVIDE, 6, 0); // Assuming this method throws ArithmeticException
            fail("Expected ArithmeticException not thrown");
        } catch (ArithmeticException e) {
            // Reprint the error message
            System.out.println("Caught error message: " + e.getMessage());
            assertEquals("Division by zero", e.getMessage());
        }
    }
    
    @Test
    public void testChainingOperations() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.ADD, new AddOperation());
        operationMap.put(Operation.SUBTRACT, new SubtractOperation());
        operationMap.put(Operation.MULTIPLY, new MultiplyOperation());
        operationMap.put(Operation.DIVIDE, new DivideOperation());

        Calculator calculator = new Calculator(operationMap);

        // Start with 5, add 3, then multiply by 2 ( (5 + 3) * 2 = 16 )
        List<Pair<Operation, Number>> operations = new ArrayList<>();
        operations.add(new Pair<>(Operation.ADD, 3));
        operations.add(new Pair<>(Operation.MULTIPLY, 2));

        double result = calculator.chainOperations(5, operations).doubleValue();  // Cast to double
        logger.info("Chaining Operations: (5 + 3) * 2 = " + result);
        assertEquals(16.0, result, 0.00000001);
    }
    
    //1. Test Addition with Zero
    @Test
    public void testAdditionWithZero() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.ADD, new AddOperation());
        Calculator calculator = new Calculator(operationMap);
        
        double result = ((Number) calculator.calculate(Operation.ADD, 5, 0)).doubleValue();  // Cast to double
        logger.info("Addition with Zero: 5 + 0 = " + result);
        assertEquals(5.0, result, 0.00000001);
    }
    
    //2. Test Subtraction with Zero
    @Test
    public void testSubtractionWithZero() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.SUBTRACT, new SubtractOperation());
        Calculator calculator = new Calculator(operationMap);
        
        double result = ((Number) calculator.calculate(Operation.SUBTRACT, 5, 0)).doubleValue();  // Cast to double
        logger.info("Subtraction with Zero: 5 - 0 = " + result);
        assertEquals(5.0, result, 0.00000001);
    }
    
    //3. Test Multiplication by Zero
    @Test
    public void testMultiplicationByZero() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.MULTIPLY, new MultiplyOperation());
        Calculator calculator = new Calculator(operationMap);
        
        double result = ((Number) calculator.calculate(Operation.MULTIPLY, 5, 0)).doubleValue();  // Cast to double
        logger.info("Multiplication by Zero: 5 * 0 = " + result);
        assertEquals(0.0, result, 0.00000001);
    }
    
    //4. Test Division of Zero
    @Test
    public void testDivisionOfZero() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.DIVIDE, new DivideOperation());
        Calculator calculator = new Calculator(operationMap);
        
        double result = ((Number) calculator.calculate(Operation.DIVIDE, 0, 5)).doubleValue();  // Cast to double
        logger.info("Division of Zero: 0 / 5 = " + result);
        assertEquals(0.0, result, 0.00000001);
    }
    
    //5. Test Large Number Addition
    @Test
    public void testLargeNumberAddition() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.ADD, new AddOperation());
        Calculator calculator = new Calculator(operationMap);

        double result = ((Number) calculator.calculate(Operation.ADD, Double.MAX_VALUE, 1)).doubleValue();  // Cast to double
        logger.info("Large Number Addition: Double.MAX_VALUE + 1 = " + result);
        assertEquals(Double.MAX_VALUE, result, 0.00000001);
    }
    
    //6. Test Large Number Multiplication
    @Test
    public void testMultiplicationWithLargeNumbers() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.MULTIPLY, new MultiplyOperation());
        Calculator calculator = new Calculator(operationMap);
        
        double result = ((Number) calculator.calculate(Operation.MULTIPLY, Double.MAX_VALUE, 2)).doubleValue();  // Cast to double
        logger.info("Multiplication with Large Numbers: Double.MAX_VALUE * 2 = " + result);
        assertEquals(Double.POSITIVE_INFINITY, result, 0.00000001);
    }
    
    //7. Test Negative Multiplication
    @Test
    public void testMultiplicationOfNegativeNumbers() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.MULTIPLY, new MultiplyOperation());
        Calculator calculator = new Calculator(operationMap);
        
        double result = ((Number) calculator.calculate(Operation.MULTIPLY, -3, -2)).doubleValue();  // Cast to double
        logger.info("Multiplication of Negative Numbers: -3 * -2 = " + result);
        assertEquals(6.0, result, 0.00000001);
    }
    
    //8. Test Division with Negative Numbers
    @Test
    public void testDivisionWithNegativeNumbers() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.DIVIDE, new DivideOperation());
        Calculator calculator = new Calculator(operationMap);
        
        double result1 = ((Number) calculator.calculate(Operation.DIVIDE, -10, 2)).doubleValue();  // Cast to double
        logger.info("Division with Negative Numbers (-10 / 2): Expected -5.0, Actual " + result1);
        assertEquals(-5.0, result1, 0.00000001);

        double result2 = ((Number) calculator.calculate(Operation.DIVIDE, 10, -2)).doubleValue();  // Cast to double
        logger.info("Division with Negative Numbers (10 / -2): Expected -5.0, Actual " + result2);
        assertEquals(-5.0, result2, 0.00000001);
    }
    
    //9. Test Division Leading to Rounding
    @Test
    public void testDivisionWithRounding() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.DIVIDE, new DivideOperation());
        Calculator calculator = new Calculator(operationMap);
        
        double result = ((Number) calculator.calculate(Operation.DIVIDE, 1, 3)).doubleValue();  // Cast to double
        logger.info("Division with Rounding: Expected 0.3333333333, Actual " + result);
        assertEquals(0.3333333333, result, 0.00000001);
    }
    
    //10. Test Unsupported Operation
    @Test
    public void testUnsupportedOperation() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        // No operation mapped here
        Calculator calculator = new Calculator(operationMap);

        logger.info("Testing Unsupported Operation.");
        
        try {
        	calculator.calculate(Operation.ADD, 5, 5); // Assuming this method throws ArithmeticException
            fail("Expected UnsupportedOperationException not thrown");
        } catch (UnsupportedOperationException e) {
            // Reprint the error message
            System.out.println("Caught error message: " + e.getMessage());
            assertEquals("Operation not supported", e.getMessage());
        }
        
    }
    
    @Test
    public void testUnsupportedDiffOperation() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.DIVIDE, new DivideOperation());
        Calculator calculator = new Calculator(operationMap);

        logger.info("Testing Undefined Operation.");
        
        try {
        	calculator.calculate(Operation.ADD, 5, 5); // Assuming this method throws ArithmeticException
            fail("Expected UnsupportedOperationException not thrown");
        } catch (UnsupportedOperationException e) {
            // Reprint the error message
            System.out.println("Caught error message: " + e.getMessage());
            assertEquals("Operation not supported", e.getMessage());
        }
        
    }
    
    @Test
    public void testUnsuportedNUllOperation() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.DIVIDE, new DivideOperation());
        Calculator calculator = new Calculator(operationMap);

        logger.info("Testing Null Operation.");
        
        try {
        	calculator.calculate(null, 5, 5); // Assuming this method throws ArithmeticException
            fail("Expected UnsupportedOperationException not thrown");
        } catch (UnsupportedOperationException e) {
            // Reprint the error message
            System.out.println("Caught error message: " + e.getMessage());
            assertEquals("Operation not supported", e.getMessage());
        }
        
    }
    
    
    //11. Test Chaining with Zero and Negative Numbers
    @Test
    public void testChainingWithZeroAndNegativeNumbers() {
        Map<Operation, OperationStrategy> operationMap = new HashMap<>();
        operationMap.put(Operation.ADD, new AddOperation());
        operationMap.put(Operation.SUBTRACT, new SubtractOperation());
        operationMap.put(Operation.MULTIPLY, new MultiplyOperation());
        operationMap.put(Operation.DIVIDE, new DivideOperation());

        Calculator calculator = new Calculator(operationMap);

        // Start with 10, subtract 0, add -5, multiply by -1 => ((10 - 0 + -5) * -1 = -5)
        List<Pair<Operation, Number>> operations = new ArrayList<>();
        operations.add(new Pair<>(Operation.SUBTRACT, 0));
        operations.add(new Pair<>(Operation.ADD, -5));
        operations.add(new Pair<>(Operation.MULTIPLY, -1));

        double result = calculator.chainOperations(10, operations).doubleValue();  // Cast to double
        logger.info("Chaining with Zero and Negative Numbers: ((10 - 0 + -5) * -1) = " + result);
        assertEquals(-5.0, result, 0.00000001);
    }
    
    
}
