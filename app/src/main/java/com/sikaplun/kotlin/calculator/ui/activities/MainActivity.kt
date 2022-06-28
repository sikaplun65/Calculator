package com.sikaplun.kotlin.calculator.ui.activities

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sikaplun.kotlin.calculator.CalculatorLandscapeScreen
import com.sikaplun.kotlin.calculator.CalculatorScreen
import com.sikaplun.kotlin.calculator.ui.theme.CalculatorTheme
import com.sikaplun.kotlin.calculator.ui.theme.MediumGray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val stateMemory = viewModel.stateMemory

                val configuration = LocalConfiguration.current

                when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        CalculatorLandscapeScreen(
                            state = state,
                            stateMemory = stateMemory,
                            onAction = viewModel::onAction,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MediumGray)
                                .padding(16.dp)
                        )
                    }
                    else -> {
                        CalculatorScreen(
                            state = state,
                            stateMemory = stateMemory,
                            onAction = viewModel::onAction,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MediumGray)
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}