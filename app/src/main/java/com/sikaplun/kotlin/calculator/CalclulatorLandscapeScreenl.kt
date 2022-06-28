package com.sikaplun.kotlin.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sikaplun.kotlin.calculator.models.CalculatorButtonLand
import com.sikaplun.kotlin.calculator.ui.theme.*

@Composable
fun CalculatorLandscapeScreen(
    state: CalculatorState,
    stateMemory: CalculatorStateMemory,
    modifier: Modifier = Modifier,
    onAction: (CalculatorAction) -> Unit,
) {
    val buttonSpacing: Dp = 4.dp

    val weight = 1F

    val textButtonModifier = Modifier
        .aspectRatio(2.2f)
        .clip(CircleShape)
        .border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))

    val numberButtonLandModifier = Modifier
        .background(Brush.verticalGradient(colors = listOf(LightGray, MediumGray)))
        .border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))
        .aspectRatio(ratio = 2.2f)

    val operationButtonLandModifier = Modifier
        .background(Brush.verticalGradient(colors = listOf(LightOrange, Orange)))
        .border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))
        .aspectRatio(ratio = 2.2f)



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
                            text = if (stateMemory.digit == "0") "" else " mr: " + stateMemory.digit,
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
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "mc", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { onAction(CalculatorAction.MemoryAddition) },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "m+", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { onAction(CalculatorAction.MemorySubtract) },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {
                    Text(text = "m−", fontSize = 10.sp)
                }

                TextButton(
                    onClick = { onAction(CalculatorAction.MemoryShow) },
                    modifier = textButtonModifier
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
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

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
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

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
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

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
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

                }

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = textButtonModifier
                        .weight(weight = weight)
                ) {

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
                        .background(Brush.verticalGradient(colors = listOf(LightBlue, Blue))),
                    onClick = {
                        onAction(CalculatorAction.Calculate)
                    }
                )
            }

        }
    }
}