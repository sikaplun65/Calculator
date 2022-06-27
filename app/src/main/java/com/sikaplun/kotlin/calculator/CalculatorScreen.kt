package com.sikaplun.kotlin.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sikaplun.kotlin.calculator.models.CalculatorButton
import com.sikaplun.kotlin.calculator.models.CalculatorButtonMemory
import com.sikaplun.kotlin.calculator.ui.theme.DarkRed
import com.sikaplun.kotlin.calculator.ui.theme.LightRed
import com.sikaplun.kotlin.calculator.util.ButtonModifiers.equalsButtonModifier
import com.sikaplun.kotlin.calculator.util.ButtonModifiers.memoryButtonModifier
import com.sikaplun.kotlin.calculator.util.ButtonModifiers.numberButtonModifier
import com.sikaplun.kotlin.calculator.util.ButtonModifiers.operationButtonModifier

@Composable
fun CalculatorScreen(
    state: CalculatorState,
    stateMemory: CalculatorStateMemory,
    modifier: Modifier = Modifier,
    onAction: (CalculatorAction) -> Unit,
) {
    val buttonSpacing: Dp = 8.dp
    val weight = 1F

    Box(modifier = modifier) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)) {
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(bottom = buttonSpacing * 2)
                    .border(1.dp, Color.White, shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .weight(1f),
                content = {
                    Column(modifier = Modifier
                        .fillMaxWidth())
                    {
                        Text(
                            text = if(stateMemory.digit=="0") "" else " mr: " + stateMemory.digit,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.3f)
                                .background(Color.Black),
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            color = Color.Gray,
                            maxLines = 1
                        )

                        Text(
                            text = state.firstOperand + (state.operation?.symbol
                                ?: "") + state.secondOperand,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1.7f)
                                .background(Color.Black),
                            fontWeight = FontWeight.Light,
                            fontSize = 50.sp,
                            color = Color.White,
                            maxLines = 2
                        )
                    }
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButtonMemory(
                    symbol = "mc",
                    modifier = memoryButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.MemoryClear)
                    }
                )

                CalculatorButtonMemory(
                    symbol = "m+",
                    modifier = memoryButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.MemoryAddition)
                    }
                )

                CalculatorButtonMemory(
                    symbol = "m-",
                    modifier = memoryButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.MemorySubtract)
                    }
                )

                CalculatorButtonMemory(
                    symbol = "mr",
                    modifier = memoryButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.MemoryShow)
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    modifier = operationButtonModifier
                        .background(Brush.verticalGradient(colors = listOf(LightRed, DarkRed)))
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Clear)
                    }
                )

                CalculatorButton(
                    symbol = "±",
                    modifier = operationButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.NumberInversion)
                    }
                )

                CalculatorButton(
                    symbol = "%",
                    modifier = operationButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.PercentCalculate)
                    }
                )

                CalculatorButton(
                    symbol = "÷",
                    modifier = operationButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "7",
                    modifier = numberButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(7))
                    }
                )
                CalculatorButton(
                    symbol = "8",
                    modifier = numberButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(8))
                    }
                )
                CalculatorButton(
                    symbol = "9",
                    modifier = numberButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(9))
                    }
                )
                CalculatorButton(
                    symbol = "×",
                    modifier = operationButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "4",
                    modifier = numberButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(4))
                    }
                )
                CalculatorButton(
                    symbol = "5",
                    modifier = numberButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(5))
                    }
                )
                CalculatorButton(
                    symbol = "6",
                    modifier = numberButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(6))
                    }
                )
                CalculatorButton(
                    symbol = "−",
                    modifier = operationButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "1",
                    modifier = numberButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(1))
                    }
                )
                CalculatorButton(
                    symbol = "2",
                    modifier = numberButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(2))
                    }
                )
                CalculatorButton(
                    symbol = "3",
                    modifier = numberButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(3))
                    }
                )
                CalculatorButton(
                    symbol = "+",
                    modifier = operationButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Addition))
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "0",
                    modifier = numberButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(0))
                    }
                )

                CalculatorButton(
                    symbol = ",",
                    modifier = numberButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Decimal)
                    }
                )

                CalculatorButton(
                    symbol = "⌫",
                    modifier = numberButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Delete)
                    }
                )

                CalculatorButton(
                    symbol = "=",
                    modifier = equalsButtonModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Calculate)
                    }
                )
            }
        }
    }
}


