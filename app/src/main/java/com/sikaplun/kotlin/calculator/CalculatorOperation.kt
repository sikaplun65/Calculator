package com.sikaplun.kotlin.calculator

sealed class CalculatorOperation(val symbol: String) {
    object Addition: CalculatorOperation("+")
    object Subtract: CalculatorOperation("-")
    object Divide: CalculatorOperation("/")
    object Multiply: CalculatorOperation("×")
}