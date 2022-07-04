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
            is CalculatorAction.PercentCalculate -> addPercentageSymbolToNumber()
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
                state.secondOperand.isNotBlank() -> {
                    val str = state.secondOperand.dropLast(1)

                    state = state.copy(secondOperand = if (str.isEmpty() || !str.contains(" ")){
                        str
                    }else{
                        groupingCharactersAfterDeletion(str)
                    })
                }
                state.operation != null -> state = state.copy(
                    operation = null
                )
                state.firstOperand.isNotBlank() -> {
                    val str = state.firstOperand.dropLast(1)

                    state = state.copy(firstOperand = if (str.isEmpty() || !str.contains(" ")){
                        str
                    } else{
                        groupingCharactersAfterDeletion(str)
                    })
                }
            }
        }
    }

    private fun performCalculation() {

        if (state.firstOperand.contains("%"))state = state.copy(operation = CalculatorOperation.Multiply)

        if (state.firstOperand.isNotEmpty() && state.operation != null && state.secondOperand.isNotEmpty() && state.secondOperand != "-") {

            val firstOperand = if (state.firstOperand.contains("%")) {
                state.firstOperand = state.firstOperand.dropLast(1)
                getNumberFromString(state.firstOperand) / 100
            } else {
                getNumberFromString(state.firstOperand)
            }

            val secondOperand =
                if (state.secondOperand.contains("%")) {
                    performDeletion()
                    getNumberFromString(state.secondOperand) * (firstOperand / 100)
                } else {
                    getNumberFromString(state.secondOperand)
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

    private fun addPercentageSymbolToNumber() {

        if (state.firstOperand.isEmpty()) return

        if (state.operation == null && state.firstOperand.isNotEmpty() && !state.firstOperand.contains("%")) {
            state = state.copy(
                firstOperand = state.firstOperand + "%",
            )

        }else if (state.secondOperand.isNotEmpty() && !state.firstOperand.contains("%")){
            state = state.copy(
                secondOperand = state.secondOperand + "%"
            )
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {

        performCalculation()

        if (state.firstOperand.isEmpty() && operation == CalculatorOperation.Subtract) {
            state = state.copy(firstOperand = "-")
            return
        }

        if (state.firstOperand.contains("%") && !state.secondOperand.contains("-")){
            state = state.copy(operation = null, secondOperand = "-")
            return
        } else if (state.firstOperand.contains("%")){
            state = state.copy(operation = null)
            return
        }

        if (state.firstOperand.isNotBlank() && state.firstOperand != "-") {
            if (state.operation == CalculatorOperation.Subtract && operation == CalculatorOperation.Subtract) {
                state = state.copy(
                    operation = CalculatorOperation.Addition
                )
                return
            } else if (state.operation == CalculatorOperation.Addition && operation == CalculatorOperation.Subtract) {
                state = state.copy(
                    operation = CalculatorOperation.Subtract
                )
                return
            } else if (state.operation == CalculatorOperation.Addition && operation == CalculatorOperation.Addition) {
                return
            } else if (state.operation == CalculatorOperation.Multiply && operation == CalculatorOperation.Multiply) {
                return
            } else if (state.operation == CalculatorOperation.Divide && operation == CalculatorOperation.Divide) {
                return
            } else if (state.operation == CalculatorOperation.Multiply && operation == CalculatorOperation.Subtract) {
                state = state.copy(secondOperand = "-")
            } else if (state.operation == CalculatorOperation.Divide && operation == CalculatorOperation.Subtract) {
                state = state.copy(secondOperand = "-")
            }
            if (state.operation != null) return
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

                if (state.firstOperand.contains("%")) {
                    state = state.copy(
                        secondOperand = groupingCharactersAfterAdding(state.secondOperand + number)
                    )
                    return
                }

                state = state.copy(
                    firstOperand = groupingCharactersAfterAdding(state.firstOperand + number)
                )
                return
            }

            if (state.secondOperand.contains("%")) {
                return
            }
            state = state.copy(
                secondOperand = groupingCharactersAfterAdding(state.secondOperand + number)
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
            if (state.firstOperand.isEmpty() || state.firstOperand == "-") {
                return
            } else if (state.firstOperand.contains("%")){
                state = state.copy(
                    operation = null,
                    secondOperand = convertNumberToString(-getNumberFromString(state.secondOperand))
                )
                return
            }

            state = state.copy(
                firstOperand = convertNumberToString(-getNumberFromString(state.firstOperand))
            )
        }
    }

    private fun getNumberFromString(str: String): Double {
        val tempStr = str.replace("\\s".toRegex(), "")
        return tempStr.replace(",", ".").toDouble()
    }

    private fun convertNumberToString(digit: Double): String {

        var str = digit.toString()
        val pointPosition = str.indexOf(".")

        if ((pointPosition-3) > 0) {

            str = str.substring(0, pointPosition - 3) + " " + str.substring(pointPosition - 3)

            for (i in 0..pointPosition / 3) {
                val lastSpacePosition = str.indexOf(" ")
                if ((lastSpacePosition - 3) > 0) {
                    str = str.substring(0, lastSpacePosition - 3) + " " + str.substring(
                        lastSpacePosition - 3)
                } else {
                    break
                }
            }
        }

        return if (digit - digit.toInt() == 0.0 ){
            str.substringBefore(".")
        }else{
            str.replace(".", ",")
        }
    }

    private fun groupingCharactersAfterAdding(str: String): String {
        var tmpStr = ""

        if (str.length > 3 && !str.contains(",")) {
            tmpStr = if (!str.contains(" ")) {
                str.substring(0, str.length - 3) + " " + str.substring(str.length - 3)
            } else {
                tmpStr = str.replace("\\s".toRegex(), "")
                tmpStr.substring(0, tmpStr.length - 3) + " " + tmpStr.substring(tmpStr.length - 3)
            }

            for (i in 0..tmpStr.length / 3) {
                val lastSpacePosition = tmpStr.indexOf(" ")
                if ((lastSpacePosition - 3) > 0) {
                    tmpStr = tmpStr.substring(0, lastSpacePosition - 3) + " " + tmpStr.substring(
                        lastSpacePosition - 3)
                } else {
                    break
                }
            }
            return tmpStr
        } else {
            return str
        }
    }

    private fun groupingCharactersAfterDeletion(str: String): String{
        var tmpStr = ""
        if (!str.contains(",")){
            if ((str.length - 3) > 0){
                tmpStr = str.replace("\\s".toRegex(),"")
                if (tmpStr.length <= 3){
                    return tmpStr
                }
                tmpStr = tmpStr.substring(0, tmpStr.length - 3) + " " + tmpStr.substring(tmpStr.length - 3)

                for (i in 0..tmpStr.length / 3) {
                    val lastSpacePosition = tmpStr.indexOf(" ")
                    if ((lastSpacePosition - 3) > 0) {
                        tmpStr =
                            tmpStr.substring(0, lastSpacePosition - 3) + " " + tmpStr.substring(
                                lastSpacePosition - 3)
                    } else {
                        break
                    }
                }
            }
            return tmpStr
        }else{
            return str
        }
    }

    private fun isNegative(operand: String): Boolean = operand.toDouble() < 0
}