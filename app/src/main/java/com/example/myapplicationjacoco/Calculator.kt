package com.example.myapplicationjacoco

class Calculator {
    // Addition
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    // Soustraction
    fun subtract(a: Int, b: Int): Int {
        return a - b
    }

    // Multiplication
    fun multiply(a: Int, b: Int): Int {
        return a * b
    }

    // Division
    fun divide(a: Int, b: Int): Int {
        if (b == 0) {
            throw IllegalArgumentException("Division par zero n'est pas permise.")
        }
        return a / b
    }

    // Modulo
    fun modulo(a: Int, b: Int): Int {
        if (b == 0) {
            throw IllegalArgumentException("Modulo par zero n'est pas permis.")
        }
        return a % b
    }

    // Puissance
    fun power(a: Int, b: Int): Int {
        return Math.pow(a.toDouble(), b.toDouble()).toInt()
    }
}