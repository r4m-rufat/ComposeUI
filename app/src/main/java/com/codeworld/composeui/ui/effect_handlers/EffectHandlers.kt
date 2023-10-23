package com.codeworld.composeui.ui.effect_handlers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codeworld.composeui.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
                }, contentAlignment = Alignment.Center
        ) {

            Text(text = "Increment")

        }

    }

}

@Composable
fun ListView(titles: List<String>, onClick: () -> Unit) {

    var selectedItem by remember {
        mutableStateOf(-1)
    }

    val lazyState = rememberLazyListState()

    val buttonVisibility by remember {
        derivedStateOf { lazyState.firstVisibleItemIndex > 0 }
    }

    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {

        LazyColumn(modifier = Modifier.fillMaxSize(), state = lazyState) {

            items(titles.size) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clickable {
                            selectedItem = it
                            onClick()
                        },
                    text = titles[it],
                    color = if (selectedItem == it) Color.Green else Color.Black
                )

            }

        }

        AnimatedVisibility(
            visible = buttonVisibility,
            enter = expandIn(animationSpec = tween(200), expandFrom = Alignment.BottomCenter),
            exit = shrinkOut(animationSpec = tween(200), shrinkTowards = Alignment.BottomCenter)
        ) {
            FloatingActionButton(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .size(50.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Color.Black)
                .padding(10.dp),
                onClick = {
                    coroutineScope.launch { lazyState.animateScrollToItem(0, 0) }
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_up),
                    contentDescription = "Arrow Up",
                    tint = Color.Green,
                    modifier = Modifier.background(Color.Black)
                )
            }
        }

    }

}