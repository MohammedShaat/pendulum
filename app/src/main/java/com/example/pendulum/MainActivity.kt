package com.example.pendulum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pendulum.ui.theme.Crystal
import com.example.pendulum.ui.theme.Desire
import com.example.pendulum.ui.theme.Honeydew
import com.example.pendulum.ui.theme.JellyBeanBlue
import com.example.pendulum.ui.theme.PendulumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    PendulumTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Desire
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Pendulum(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    bgColor = Desire,
                    boxColor = Honeydew,
                    circleColor = JellyBeanBlue,
                    eyeColor = Honeydew,
                    pupilColor = Color.Black,
                    threadColor = Color.Black,
                    ballColor = Crystal
                )
                Spacer(modifier = Modifier.height(60.dp))
                Time(
                    modifier = Modifier.fillMaxWidth(),
                    color = Honeydew
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PendulumTheme {
        App()
    }
}