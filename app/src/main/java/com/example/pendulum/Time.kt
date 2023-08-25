package com.example.pendulum

import android.os.Handler
import android.os.Looper
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.pendulum.ui.theme.PendulumTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun Time(
    modifier: Modifier = Modifier,
    color: Color = Color.Black
) {
    val formatter = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
    var time by remember { mutableStateOf(formatter.format(Date())) }

    val handler = Handler(Looper.getMainLooper())
    val runnable = object : Runnable {
        override fun run() {
            time = formatter.format(Date())
            handler.postDelayed(this, 1000)
        }
    }
    handler.post(runnable)

    Text(
        modifier = modifier,
        text = time,
        style = MaterialTheme.typography.headlineLarge.copy(
            textAlign = TextAlign.Center,
            color = color,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TimePreview() {
    PendulumTheme {
        Time()
    }
}