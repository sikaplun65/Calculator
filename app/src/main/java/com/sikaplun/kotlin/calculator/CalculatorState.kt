package com.sikaplun.kotlin.calculator

data class CalculatorState(
    val firstOperand: String = "",
    val secondOperand: String = "",
    val operation: CalculatorOperation? = null,
)

data class CalculatorStateMemory(
    val digit: String = "0",
)
