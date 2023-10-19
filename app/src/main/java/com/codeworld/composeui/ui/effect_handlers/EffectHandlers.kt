package com.codeworld.composeui.ui.effect_handlers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LaunchedEffectHandler(viewModel: EffectHandlersViewModel) {

    LaunchedEffect(key1 = true) {

        viewModel._value.collectLatest {

        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "${viewModel._value.collectAsState().value}",
            style = MaterialTheme.typography.displayLarge
        )

        Box(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .clickable {
                    viewModel.incrementValue()
                },
            contentAlignment = Alignment.Center
        ) {

            Text(text = "Increment")

        }

    }

}