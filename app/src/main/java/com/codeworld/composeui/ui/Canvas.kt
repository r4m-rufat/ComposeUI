package com.codeworld.composeui.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codeworld.composeui.R
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun MakeFigure(points: List<Offset>) {

    Canvas(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
    ) {

        drawPath(path = Path().apply {
            moveTo(points[0].x, points[0].y)
            curve(points[0], points[1])
            curve(points[1], points[2])
            curve(points[2], points[3])
            curve(points[3], points[4])
            lineTo(size.width, size.height)
            lineTo(0F, size.height)
        }, color = Color.Green)

        val circleCenter = Offset(size.width * 0.7F, size.height * 0.3F)
        drawCircle(
            center = circleCenter,
            brush = Brush.radialGradient(
                colors = listOf(
                    Color.Red, Color.Yellow
                ), center = circleCenter, radius = 50.dp.toPx()
            ),
            radius = 50.dp.toPx(),
        )

    }

}

@Composable
fun TimerMaker(totalTime: Long) {

    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    var fractionalValue by remember {
        mutableStateOf(currentTime / totalTime.toFloat())
    }

    LaunchedEffect(key1 = currentTime) {
        delay(100L)
        if (currentTime > 0) {
            currentTime -= 100L
            fractionalValue = currentTime / totalTime.toFloat()
            Log.d(TAG, "TimerMaker: Timer Maker -> $fractionalValue")
        }
    }

    Box(
        modifier = Modifier.size(250.dp), contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier.size(200.dp)
        ) {

            drawArc(
                color = Color.DarkGray.copy(alpha = 0.7f),
                startAngle = 135F,
                sweepAngle = 270F,
                useCenter = false,
                size = Size(200.dp.toPx(), 200.dp.toPx()),
                style = Stroke(
                    width = 8.dp.toPx(), cap = StrokeCap.Round
                )
            )

            drawArc(
                brush = Brush.horizontalGradient(
                    colorStops = arrayOf(
                        0.0F to Color(0xFFE90000),
                        0.3F to Color(0xFFE98C00),
                        0.5F to Color(0xFFFFFB00),
                        0.75F to Color.Yellow,
                        1F to Color.Green
                    ), endX = 100.dp.toPx()
                ),
                startAngle = 135F,
                sweepAngle = 270F * fractionalValue,
                useCenter = false,
                size = Size(200.dp.toPx(), 200.dp.toPx()),
                style = Stroke(
                    width = 8.dp.toPx(), cap = StrokeCap.Round
                )
            )

            val centerOffset = Offset(size.width / 2f, size.height / 2f)
            val radius = 100.dp.toPx()
            val x = cos((270F * fractionalValue + 135) * (PI / 180f).toFloat()) * radius
            val y = sin((270F * fractionalValue + 135) * (PI / 180f).toFloat()) * radius

            drawCircle(
                color = Color.Green,
                radius = 12.dp.toPx(),
                center = Offset(centerOffset.x + x, centerOffset.y + y)
            )
        }

        Text(
            text = "${currentTime / 1000L}", style = MaterialTheme.typography.displayLarge
        )

    }

}

@Composable
fun ShimmerAnimation() {

    val transition = rememberInfiniteTransition()

    val start = transition.animateFloat(
        initialValue = -100f, targetValue = 1200f, animationSpec = InfiniteRepeatableSpec(
            animation = tween(2000), repeatMode = RepeatMode.Restart
        )
    )

    val end = transition.animateFloat(
        initialValue = 0f, targetValue = 1300f, animationSpec = InfiniteRepeatableSpec(
            animation = tween(2000), repeatMode = RepeatMode.Restart
        )
    )

    val color = Brush.linearGradient(
        colors = listOf(
            Color(0xFFF7F7F7), Color(0xCD414141), Color(0xFFF7F7F7)
        ),
        start = Offset(start.value, start.value),
        end = Offset(end.value,end.value)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(brush = color)
    ) {

    }

}

@Composable
fun RotateSun() {

    val transition = rememberInfiniteTransition()

    val rotationAngle by transition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = InfiniteRepeatableSpec(
            tween(10 * 1000, easing = LinearEasing), repeatMode = RepeatMode.Restart
        )
    )


    Icon(
        painter = painterResource(id = R.drawable.ic_sun),
        contentDescription = null,
        modifier = Modifier
            .padding(10.dp)
            .size(100.dp)
            .rotate(rotationAngle)
    )

}

fun Path.curve(first: Offset, second: Offset) {

    quadraticBezierTo(
        first.x,
        first.y,
        sqrt((first.x * second.x).toDouble()).toFloat(),
        sqrt((first.y * second.y).toDouble()).toFloat()
    )

}