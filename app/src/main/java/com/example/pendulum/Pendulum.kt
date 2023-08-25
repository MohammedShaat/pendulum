package com.example.pendulum

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import com.example.pendulum.ui.theme.PendulumTheme

@Composable
fun Pendulum(
    modifier: Modifier = Modifier,
    bgColor: Color = Color.White,
    boxColor: Color = Color.Yellow,
    circleColor: Color = Color.Blue,
    eyeColor: Color = Color.White,
    pupilColor: Color = Color.Black,
    threadColor: Color = Color.Black,
    ballColor: Color = Color.Cyan
) {
    var width by remember { mutableStateOf(0f) }
    var height by remember { mutableStateOf(0f) }
    val centerX by remember(width) { mutableStateOf(width / 2) }
    val clockRadius by remember { mutableStateOf(180f) }
    val clockY by remember(height) { mutableStateOf(height / 3) }
    val leftEyeX by remember(centerX) { mutableStateOf(centerX - (width / 12)) }
    val rightEyeX by remember(centerX) { mutableStateOf(centerX + (width / 12)) }
    val eyeSize by remember { mutableStateOf(40f) }
    val pupilSize by remember { mutableStateOf(15f) }
    val threadLength by remember { mutableStateOf(700f) }
    val boxSize by remember { mutableStateOf(Size(500f, 600f)) }

    val animationDuration by remember { mutableStateOf(2000) }
    val infiniteTransition = rememberInfiniteTransition("pendulum")
    val pupilsRotation by infiniteTransition.animateFloat(
        initialValue = 35f,
        targetValue = -35f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), "pupils_translation"
    )
    val pendulumRotation by infiniteTransition.animateFloat(
        initialValue = 10f,
        targetValue = -10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), "pendulum_rotation"
    )

    Box(
        modifier = modifier
            .background(color = bgColor)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            width = size.width
            height = size.height

            // Pendulum
            rotate(
                degrees = pendulumRotation,
                pivot = Offset(centerX, clockY)
            ) {   // pendulumRotation
                // Thread
                drawLine(
                    color = threadColor,
                    start = Offset(centerX, clockY),
                    end = Offset(centerX, clockY + threadLength),
                    strokeWidth = 8f
                )
                // Ball
                drawCircle(
                    color = ballColor,
                    center = Offset(centerX, clockY + threadLength),
                    radius = 50f
                )
            }

            // Clock
            // Box
            drawRoundRect(
                color = boxColor,
                topLeft = Offset(centerX - boxSize.width / 2, clockY - boxSize.height / 2),
                size = boxSize,
                cornerRadius = CornerRadius(40f)
            )
            // Circle
            drawCircle(
                color = circleColor,
                center = Offset(width / 2, clockY),
                radius = clockRadius
            )
            // Left eye
            drawCircle(
                color = eyeColor,
                center = Offset(leftEyeX, clockY),
                radius = eyeSize
            )
            // Right eye
            drawCircle(
                color = eyeColor,
                center = Offset(rightEyeX, clockY),
                radius = eyeSize
            )

            // Left pupil
            rotate(degrees = pupilsRotation, pivot = Offset(leftEyeX, clockY - 10)) {
                drawCircle(
                    color = pupilColor,
                    center = Offset(leftEyeX, clockY + 18),
                    radius = pupilSize
                )
            }
            // Right pupil
            rotate(degrees = pupilsRotation, pivot = Offset(rightEyeX, clockY - 10)) {
                drawCircle(
                    color = pupilColor,
                    center = Offset(rightEyeX, clockY + 18),
                    radius = pupilSize
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PendulumPreview() {
    PendulumTheme {
        Pendulum()
    }
}