package com.sikaplun.kotlin.calculator

data class CalculatorState(
    val firstOperand: String = "",
    val secondOperand: String = "",
    val operation: CalculatorOperation? = null
)
