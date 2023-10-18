package com.codeworld.composeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codeworld.composeui.ui.MainScreen
import com.codeworld.composeui.ui.MakeFigure
import com.codeworld.composeui.ui.RotateSun
import com.codeworld.composeui.ui.ShimmerAnimation
import com.codeworld.composeui.ui.theme.ComposeUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                            Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {

                                ShimmerAnimation()
                                
                            }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeUITheme {
//        MakeFigure(points = listOf(
//            Offset(0F, 200F),
//            Offset(200F, 100F),
//            Offset(300F, 400F),
//            Offset(500F, 300F),
//            Offset(1500F, 500F),
//        ))
//        TimerMaker(totalTime = 60 * 1000L)

        ShimmerAnimation()

    }
}
