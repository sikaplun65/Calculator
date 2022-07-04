package com.sikaplun.kotlin.calculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
import com.sikaplun.kotlin.calculator.CalculatorAction
import com.sikaplun.kotlin.calculator.CalculatorOperation
import com.sikaplun.kotlin.calculator.CalculatorState
import com.sikaplun.kotlin.calculator.CalculatorStateMemory
import com.sikaplun.kotlin.calculator.models.CalculatorButtonLand
import com.sikaplun.kotlin.calculator.ui.theme.MediumBlue
import com.sikaplun.kotlin.calculator.ui.theme.DarkRed
import com.sikaplun.kotlin.calculator.ui.theme.LightBlue
import com.sikaplun.kotlin.calculator.ui.theme.LightRed
import com.sikaplun.kotlin.calculator.util.ButtonModifiers.numberButtonLandModifier
import com.sikaplun.kotlin.calculator.util.ButtonModifiers.operationButtonLandModifier
import com.sikaplun.kotlin.calculator.util.ButtonModifiers.textButtonLandModifier

@Composable
fun CalculatorLandscapeScreen(
    state: CalculatorState,
    stateMemory: CalculatorStateMemory,
    modifier: Modifier = Modifier,
    onAction: (CalculatorAction) -> Unit,
) {
    val buttonSpacing: Dp = 4.dp
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
                            text = if (stateMemory.digit == "0") "" else "  mr: " + stateMemory.digit,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.5f)
                                .background(Color.Black),
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.Gray,
                            maxLines = 1
                        )

                        Text(
                            text = state.firstOperand + (state.operation?.symbol
                                ?: "") + state.secondOperand + " ",
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1.5f)
                                .background(Color.Black),
                            fontWeight = FontWeight.Light,
                            fontSize = 30.sp,
                            color = Color.White,
                            maxLines = 1
                        )
                    }
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                TextButton(

                    onClick = { onAction(CalculatorAction.MemoryClear) },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "mc", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { onAction(CalculatorAction.MemoryAddition) },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "m+", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { onAction(CalculatorAction.MemorySubtract) },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "m−", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { onAction(CalculatorAction.MemoryShow) },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "mr", fontSize = 10.sp)
                }

                CalculatorButtonLand(
                    symbol = "AC",
                    modifier = operationButtonLandModifier
                        .background(Brush.verticalGradient(colors = listOf(LightRed, DarkRed)))
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Clear)
                    }
                )

                CalculatorButtonLand(
                    symbol = "±",
                    modifier = operationButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.NumberInversion)
                    }
                )

                CalculatorButtonLand(
                    symbol = "%",
                    modifier = operationButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.PercentCalculate)
                    }
                )

                CalculatorButtonLand(
                    symbol = "÷",
                    modifier = operationButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "(", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = ")", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "√", fontSize = 14.sp)
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "^", fontSize = 14.sp)
                }

                CalculatorButtonLand(
                    symbol = "7",
                    modifier = numberButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(7))
                    }
                )

                CalculatorButtonLand(
                    symbol = "8",
                    modifier = numberButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(8))
                    }
                )

                CalculatorButtonLand(
                    symbol = "9",
                    modifier = numberButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(9))
                    }
                )

                CalculatorButtonLand(
                    symbol = "×",
                    modifier = operationButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "sin", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "cos", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "tan", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "log", fontSize = 10.sp)
                }

                CalculatorButtonLand(
                    symbol = "4",
                    modifier = numberButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(4))
                    }
                )

                CalculatorButtonLand(
                    symbol = "5",
                    modifier = numberButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(5))
                    }
                )

                CalculatorButtonLand(
                    symbol = "6",
                    modifier = numberButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(6))
                    }
                )

                CalculatorButtonLand(
                    symbol = "−",
                    modifier = operationButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "π", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "!", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "e", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "1/x", fontSize = 10.sp)
                }

                CalculatorButtonLand(
                    symbol = "3",
                    modifier = numberButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(3))
                    }
                )

                CalculatorButtonLand(
                    symbol = "2",
                    modifier = numberButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(2))
                    }
                )

                CalculatorButtonLand(
                    symbol = "1",
                    modifier = numberButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(1))
                    }
                )

                CalculatorButtonLand(
                    symbol = "+",
                    modifier = operationButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Addition))
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "Rad", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "ln", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "EE", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonLandModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "Rand", fontSize = 10.sp)
                }

                CalculatorButtonLand(
                    symbol = "0",
                    modifier = numberButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Number(0))
                    }
                )

                CalculatorButtonLand(
                    symbol = ",",
                    modifier = numberButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Decimal)
                    }
                )

                CalculatorButtonLand(
                    symbol = "⌫",
                    modifier = numberButtonLandModifier
                        .weight(weight = weight),
                    onClick = {
                        onAction(CalculatorAction.Delete)
                    }
                )

                CalculatorButtonLand(
                    symbol = "=",
                    modifier = operationButtonLandModifier
                        .weight(weight = weight)
                        .background(Brush.verticalGradient(colors = listOf(LightBlue, MediumBlue))),
                    onClick = {
                        onAction(CalculatorAction.Calculate)
                    }
                )
            }

        }
    }
}