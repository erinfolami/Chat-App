package com.example.arya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.arya.screens.chat.ChatScreen
import com.example.arya.ui.theme.ARYATheme
import linearGradientBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ARYATheme {
                Scaffold(modifier = Modifier.fillMaxSize().) { innerPadding ->
                    ChatScreen(
                        modifier = Modifier.padding(innerPadding),
                        backgroundModifier = Modifier.linearGradientBackground()
                    )

                }
            }
        }
    }
}

fun linearGradientBackground(): Modifier = this.drawBehind {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF10A1F6), Color(0xFFE9C39B)), // Use the provided colors
        start = Offset.Zero,
        end = Offset(size.width, size.height)
    )
    drawRect(brush = gradient)
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
    ARYATheme {
        Greeting("Android")
    }
}