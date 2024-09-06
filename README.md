# FlexibleCalculator

# Flexible Calculator Project

## Overview

The Flexible Calculator is a Java-based application designed to support different mathematical operations using the **Strategy design pattern**. It follows the principles of **Inversion of Control (IoC)**, making it extensible, testable, and easy to integrate into different environments. The calculator supports various arithmetic operations and allows for chaining multiple operations.

## Features
- **Basic Operations:** Addition, Subtraction, Multiplication, Division.
- **Flexible and Extensible Design:** Supports easy addition of new operations without changing the core `Calculator` class.
- **Chaining of Operations:** Allows multiple operations to be performed sequentially on a number.
- **IoC Compatibility:** The design is compatible with IoC frameworks, allowing for external management of dependencies.
- **Graceful Error Handling:** Handles unsupported operations and invalid input with meaningful error messages.

## Assumptions
1. **Operations as Enum:** 
   The supported operations (`ADD`, `SUBTRACT`, `MULTIPLY`, `DIVIDE`) are represented as an `enum` (`Operation`). It is assumed that new operations can be easily added to the `enum` when required.
   
2. **Strategy Pattern for Operations:**
   Each operation (`ADD`, `SUBTRACT`, `MULTIPLY`, `DIVIDE`) has its own implementation class following the `OperationStrategy` interface. This allows easy swapping of implementations for each operation without modifying the `Calculator` class.

3. **Error Handling for Unsupported Operations:**
   If an unsupported operation is requested, the calculator throws an `UnsupportedOperationException` with a meaningful message. We assume the application using this calculator will handle this exception or log it appropriately.

4. **Numerical Input Assumptions:**
   The `Number` type is used for all inputs. It is assumed that the caller will provide valid `Number` instances (e.g., integers or floating-point numbers). The calculator does not handle complex numbers or non-numeric input.

5. **No State Management:**
   The `Calculator` class itself does not maintain any state beyond the operations it supports. Each calculation is stateless, which simplifies usage and avoids concurrency issues.

## Design Decisions
### 1. **Inversion of Control (IoC) Compatibility:**
The `Calculator` class uses constructor-based dependency injection to allow for easy management of operations from external frameworks. This ensures that the `Calculator` is decoupled from the specific implementations of operations, allowing for extensibility and easy unit testing.

### 2. **Graceful Handling of Invalid Operations:**
Unsupported operations are handled by throwing a specific exception (`UnsupportedOperationException`) with a detailed message. This helps in identifying exactly which operation was unsupported.

### 3. **Chaining Operations:**
The calculator allows for chaining operations sequentially on a number. This is achieved via the `chainOperations` method, which takes an initial number and a list of operations, applying them one after the other.

### 4. **Exception Handling Strategy:**
Error handling is done at the application level. In the core `Calculator` class, exceptions are thrown for invalid operations (e.g., division by zero or unsupported operations) but not handled internally, allowing for flexible error management by the caller.

## How to Use
### 1. **Calculator Class:**
You can use the `Calculator` class to perform arithmetic operations by injecting a map of `Operation` to `OperationStrategy`.

```java
Map<Operation, OperationStrategy> operationMap = new HashMap<>();
operationMap.put(Operation.ADD, new AddOperation());
operationMap.put(Operation.SUBTRACT, new SubtractOperation());

Calculator calculator = new Calculator(operationMap);
Number result = calculator.calculate(Operation.ADD, 5, 3);  // Returns 8
```

### 2. **Chaining Operations:**
```java
List<Pair<Operation, Number>> operations = new ArrayList<>();
operations.add(new Pair<>(Operation.ADD, 5));
operations.add(new Pair<>(Operation.MULTIPLY, 2));

Number result = calculator.chainOperations(10, operations);  // Returns (10 + 5) * 2 = 30
```

### 3. **Handling Unsupported Operations:**
```java
try {
    calculator.calculate(Operation.DIVIDE, 5, 0);  // Throws ArithmeticException
} catch (ArithmeticException e) {
    System.out.println("Caught error: " + e.getMessage());
}
```

## Running Unit Tests

### Option 1: Running Tests via IDE (e.g., IntelliJ IDEA or Eclipse)
1. **Open `CalculatorTest.java`:** Navigate to the `CalculatorTest.java` class in your IDE.
2. **Right-click the class or method:**
   - Right-click anywhere inside the `CalculatorTest.java` file.
   - Select **Run as → JUnit Test** (in Eclipse) or **Run 'CalculatorTest'** (in IntelliJ IDEA).
3. **View Test Results:**
   - The IDE will execute all the tests within `CalculatorTest.java`.
   - You should see a test runner view showing which tests passed or failed.

### What the Tests Cover:
- **Basic Operations:**
   - Tests for addition, subtraction, multiplication, and division.
- **Edge Cases:**
   - Handles scenarios like division by zero and unsupported operations.
- **Chaining Operations:**
   - Ensures that multiple operations can be chained together correctly.
- **Error Handling:**
   - Tests for meaningful error messages when operations are unsupported or invalid.

## Future Enhancements
- **Support for Additional Operations:** New operations like modulus, power, square root, etc., can be easily added by implementing new strategies.
- **Support for Complex Numbers:** Extend the calculator to handle complex numbers and other numerical types.
- **Integration with IoC Frameworks:** Integrate with popular IoC frameworks like Spring for managing dependencies automatically.

## Conclusion
The Flexible Calculator project provides a modular and extensible design, enabling support for various operations in a loosely coupled way. Its IoC compatibility ensures it can be easily integrated into different environments, making it ideal for both standalone applications and complex systems.
