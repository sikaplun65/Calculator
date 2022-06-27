package com.sikaplun.kotlin.calculator.ui.activities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sikaplun.kotlin.calculator.CalculatorAction
import com.sikaplun.kotlin.calculator.CalculatorOperation
import com.sikaplun.kotlin.calculator.CalculatorState
import com.sikaplun.kotlin.calculator.CalculatorStateMemory

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    var stateMemory by mutableStateOf(CalculatorStateMemory())
        private set

    private var isNumberChange = true


    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> clearScreen()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.NumberInversion -> inversionPositiveDigitToNegativeDigitAndViceVersa()
            is CalculatorAction.MemoryClear -> clearMemory()
            is CalculatorAction.MemoryShow -> showNumberFromMemory()
            is CalculatorAction.MemoryAddition -> addToNumberFromMemory()
            is CalculatorAction.MemorySubtract -> subtractFromNumberInMemory()
            is CalculatorAction.PercentCalculate -> performPercentCalculation()
        }
    }

    private fun clearMemory() {
        stateMemory = CalculatorStateMemory()
    }

    private fun subtractFromNumberInMemory() {
        if (state.firstOperand.isNotEmpty() && state.operation != null && state.secondOperand.isEmpty()) return

        if (state.secondOperand.isNotEmpty()) {
            performCalculation()
        }
        val num = getNumberFromString(stateMemory.digit) - getNumberFromString(state.firstOperand)
        stateMemory = stateMemory.copy(digit = convertNumberToString(num))

    }

    private fun addToNumberFromMemory() {
        if (state.firstOperand.isNotEmpty() && state.operation != null && state.secondOperand.isEmpty()) return

        if (state.secondOperand.isNotEmpty()) {
            performCalculation()
        }

        val num = getNumberFromString(stateMemory.digit) + getNumberFromString(state.firstOperand)
        stateMemory = stateMemory.copy(digit = convertNumberToString(num))

    }

    private fun showNumberFromMemory() {

        if (state.firstOperand.isEmpty()) {
            state = state.copy(
                firstOperand = stateMemory.digit
            )
        } else if (state.firstOperand.isNotEmpty() && state.operation == null) {
            state = state.copy(
                firstOperand = stateMemory.digit
            )
        } else if (state.secondOperand.isEmpty()) {
            state = state.copy(
                secondOperand = stateMemory.digit
            )
        }
    }

    private fun clearScreen() {
        isNumberChange = true
        state = CalculatorState()
    }

    private fun performDeletion() {
        if (isNumberChange) {
            when {
                state.secondOperand.isNotBlank() -> state = state.copy(
                    secondOperand = state.secondOperand.dropLast(1)
                )
                state.operation != null -> state = state.copy(
                    operation = null
                )
                state.firstOperand.isNotBlank() -> state = state.copy(
                    firstOperand = state.firstOperand.dropLast(1)
                )
            }
        }
    }

    private fun performCalculation() {

        if (state.firstOperand.isNotEmpty() && state.operation != null) {

            val firstOperand = getNumberFromString(state.firstOperand)
            var secondOperand = getNumberFromString(state.firstOperand)

            if (state.secondOperand.isNotEmpty() && state.secondOperand.contains("%")){
                performDeletion()
                secondOperand = getNumberFromString(state.secondOperand)*(firstOperand/100)
            } else if(state.secondOperand.isNotEmpty()) {
                secondOperand = getNumberFromString(state.secondOperand)
            }

            val result = when (state.operation) {
                is CalculatorOperation.Addition -> firstOperand + secondOperand
                is CalculatorOperation.Subtract -> firstOperand - secondOperand
                is CalculatorOperation.Multiply -> firstOperand * secondOperand
                is CalculatorOperation.Divide -> firstOperand / secondOperand
                null -> return
            }

            clearScreen()
            state = state.copy(
                firstOperand = convertNumberToString(result)
            )
            isNumberChange = false
        }
    }

    private fun performPercentCalculation() {

        if (state.firstOperand.isEmpty()) return

        val num = getNumberFromString(state.firstOperand)

        if (state.operation == null && state.secondOperand.isEmpty()) {
            state = state.copy(
                firstOperand = convertNumberToString(num / 100)
            )
        }else if (state.secondOperand.isNotEmpty()){
            state = state.copy(
                secondOperand = state.secondOperand + "%"
            )
        }

    }

    private fun enterOperation(operation: CalculatorOperation) {

        performCalculation()

        if (state.firstOperand.isNotBlank()) {
            state = state.copy(operation = operation)

            isNumberChange = true
        }
    }

    private fun enterDecimal() {
        if (state.operation == null) {
            if (state.firstOperand.isEmpty()) {
                state = state.copy(firstOperand = "0,")
                return
            }
            if (!state.firstOperand.contains(",")) {
                state = state.copy(
                    firstOperand = state.firstOperand + ","
                )
                return
            }
        }

        if (state.secondOperand.isEmpty()) {
            state = state.copy(secondOperand = "0,")
            return
        }
        if (!state.secondOperand.contains(",")) {
            state = state.copy(
                secondOperand = state.secondOperand + ","
            )
        }
    }

    private fun enterNumber(number: Int) {
        if (isNumberChange) {
            if (state.operation == null) {
                if (state.firstOperand.length >= MAX_NUM_LENGTH) {
                    return
                }
                if (state.firstOperand == "0") {
                    state = state.copy(
                        firstOperand = number.toString()
                    )
                    return
                }
                state = state.copy(
                    firstOperand = state.firstOperand + number
                )
                return
            }
            if (state.secondOperand.length >= MAX_NUM_LENGTH) {
                return
            }
            if (state.secondOperand == "0") {
                state = state.copy(
                    secondOperand = number.toString()
                )
                return
            }
            state = state.copy(
                secondOperand = state.secondOperand + number
            )
        }
    }


    private fun inversionPositiveDigitToNegativeDigitAndViceVersa() {

        if (state.operation != null) {
            if (state.secondOperand.isEmpty()) {
                return
            } else if (state.secondOperand.isNotEmpty() && state.operation != CalculatorOperation.Subtract
                && state.operation != CalculatorOperation.Addition
            ) {
                state = state.copy(
                    secondOperand = convertNumberToString(-getNumberFromString(state.secondOperand))
                )
            } else if (state.secondOperand.isNotEmpty() && state.operation == CalculatorOperation.Addition) {
                state = state.copy(
                    operation = CalculatorOperation.Subtract
                )

            } else {
                if (state.operation == CalculatorOperation.Subtract && !isNegative(state.secondOperand)) {
                    state = state.copy(
                        operation = CalculatorOperation.Addition
                    )
                } else if (state.operation == CalculatorOperation.Subtract && isNegative(state.secondOperand)) {
                    state = state.copy(
                        operation = CalculatorOperation.Addition,
                        secondOperand = convertNumberToString(getNumberFromString(state.secondOperand))
                    )
                }
            }
        } else {
            if (state.firstOperand.isEmpty()) {
                return
            }

            state = state.copy(
                firstOperand = convertNumberToString(-getNumberFromString(state.firstOperand))
            )
        }
    }

    private fun getNumberFromString(str: String): Double {
        return str.replace(",", ".", true).toDouble()
    }

    private fun convertNumberToString(digit: Double): String {
        return if (digit - digit.toInt() == 0.0) digit.toInt().toString()
        else digit.toString().take(7).replace(".", ",", true)
    }


    private fun isNegative(operand: String): Boolean = operand.toDouble() < 0


    companion object {
        private const val MAX_NUM_LENGTH = 9
    }
}