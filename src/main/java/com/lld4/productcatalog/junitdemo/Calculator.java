package com.lld4.productcatalog.junitdemo;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public double divide(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Divide by zero");
        }

    }
}
