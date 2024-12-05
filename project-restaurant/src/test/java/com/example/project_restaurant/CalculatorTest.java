package com.example.project_restaurant;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    Calculator calculator;
    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }
    @Test
    public void testAdd() {
      Assertions.assertEquals(5, calculator.add(2, 3));
      Assertions.assertEquals(2, calculator.add(2, 0));
    }
}
