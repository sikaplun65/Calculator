package com.sikaplun.kotlin.calculator

data class CalculatorState(
    var firstOperand: String = "",
    val secondOperand: String = "",
    var operation: CalculatorOperation? = null,
)

data class CalculatorStateMemory(
    val digit: String = "0",
)
