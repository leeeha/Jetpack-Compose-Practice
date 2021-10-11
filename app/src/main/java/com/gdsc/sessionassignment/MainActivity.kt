package com.gdsc.sessionassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var progressCount by remember { mutableStateOf(0.0f) }
    val progressCountAnimation by animateFloatAsState(
        targetValue = progressCount,
        tween(
            durationMillis = 300,
            delayMillis = 10,
            easing = LinearOutSlowInEasing
        )
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(150.dp))

        ProgressBarText(progressCount) // 0 / 10에서 시작
        Spacer(modifier = Modifier.height(30.dp))

        LinearProgressBar(progressCountAnimation)
        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {
            if(progressCount < 10)
                progressCount += 1
        }){
            Text("다음 카드로 넘어가기")
        }
    }
}

@Composable
fun ProgressBarText(progressCount: Float) {
    Text("${progressCount.toInt()} / 10", style = MaterialTheme.typography.h5)
}

@Composable
fun LinearProgressBar(progressCountAnimation: Float) {
    LinearProgressIndicator(progress = progressCountAnimation / 10)
}
