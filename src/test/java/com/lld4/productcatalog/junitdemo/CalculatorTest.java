package com.lld4.productcatalog.junitdemo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void testAddTwoValuePositiveCase() {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        assertEquals(3, result);

    }

    @Test
    public void testDividePositiveCase() {
        Calculator calculator = new Calculator();
        double result = calculator.divide(11, 2);
        assertEquals(5, result);

    }

    @Test
    public void testDivideNegativeCase() {
        Calculator calculator = new Calculator();
        double result = calculator.divide(-1, 2);
        assertEquals(-0, result);
    }
    @Test
    public void testDivideByZero() {
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class, () -> calculator.divide(0, 0));

    }
}