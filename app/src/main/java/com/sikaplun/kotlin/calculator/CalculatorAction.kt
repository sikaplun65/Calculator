package com.sikaplun.kotlin.calculator

sealed class CalculatorAction{
    data class Number(val number: Int): CalculatorAction()
    data class Operation(val operation: CalculatorOperation): CalculatorAction()
    object Clear: CalculatorAction()
    object Delete: CalculatorAction()
    object Decimal: CalculatorAction()
    object Calculate: CalculatorAction()
    object NumberInversion: CalculatorAction()
    object MemoryAddition:CalculatorAction()
    object MemorySubtract:CalculatorAction()
    object MemoryClear: CalculatorAction()
    object MemoryShow: CalculatorAction()
    object PercentCalculate: CalculatorAction()
    object BackgroundMR: CalculatorAction()

}
