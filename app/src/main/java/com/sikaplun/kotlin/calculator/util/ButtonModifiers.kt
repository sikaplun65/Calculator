package com.sikaplun.kotlin.calculator.util

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sikaplun.kotlin.calculator.ui.theme.*

object ButtonModifiers {
    val numberButtonModifier = Modifier
        .background(Brush.verticalGradient(colors = listOf(LightGray,
            Color.DarkGray)))
        .border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))
        .aspectRatio(ratio = 1.4F)

    val operationButtonModifier = Modifier
        .background(Brush.verticalGradient(colors = listOf(LightOrange, Orange)))
        .border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))
        .aspectRatio(ratio = 1.4F)

    val memoryButtonModifier = Modifier
        .background(Brush.verticalGradient(colors = listOf(MediumBlue, Green)))
        .border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))
        .aspectRatio(ratio = 2.1f)

    val equalsButtonModifier = Modifier
        .background(Brush.verticalGradient(colors = listOf(LightBlue,
            MediumBlue)))
        .border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))
        .aspectRatio(ratio = 1.4f)

    // landscape orientation
    val textButtonLandModifier = Modifier
        .aspectRatio(1.5f)
        .clip(CircleShape)
        .border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))

    val memoryButtonModifierLand = Modifier
        .background(Brush.verticalGradient(colors = listOf(MediumBlue, Green)))
        .border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))
        .aspectRatio(ratio = 1.5f)

    val numberButtonLandModifier = Modifier
        .background(Brush.verticalGradient(colors = listOf(LightGray, MediumGray)))
        .border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))
        .aspectRatio(ratio = 1.5f)

    val operationButtonLandModifier = Modifier
        .background(Brush.verticalGradient(colors = listOf(LightOrange, Orange)))
        .border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))
        .aspectRatio(ratio = 1.5f)
}