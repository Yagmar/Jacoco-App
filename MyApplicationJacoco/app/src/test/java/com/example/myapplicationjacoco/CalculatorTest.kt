package com.example.myapplicationjacoco

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.Assert.assertThrows

class CalculatorTest {

    @Test
    fun `test add function`() {
        val calculator = Calculator()
        val result = calculator.add(2, 3)
        assertEquals(5, result)
    }

    @Test
    fun `test subtract function`() {
        val calculator = Calculator()
        val result = calculator.subtract(5, 3)
        assertEquals(2, result)
    }

    @Test
    fun `test multiply function`() {
        val calculator = Calculator()
        val result = calculator.multiply(4, 3)
        assertEquals(12, result)
    }

    @Test
    fun `test divide function`() {
        val calculator = Calculator()
        val result = calculator.divide(6, 3)
        assertEquals(2, result)
    }

    @Test
    fun `test divide by zero`() {
        val calculator = Calculator()
        val exception = assertThrows(IllegalArgumentException::class.java) {
            calculator.divide(6, 0)
        }
        assertEquals("Division par zero n'est pas permise.", exception.message)
    }

    @Test
    fun `test modulo function`() {
        val calculator = Calculator()
        val result = calculator.modulo(10, 3)
        assertEquals(1, result)
    }

    @Test
    fun `test modulo by zero`() {
        val calculator = Calculator()
        val exception = assertThrows(IllegalArgumentException::class.java) {
            calculator.modulo(10, 0)
        }
        assertEquals("Modulo par zero n'est pas permis.", exception.message)
    }

    @Test
    fun `test power function`() {
        val calculator = Calculator()
        val result = calculator.power(2, 3)
        assertEquals(8, result)
    }
}
