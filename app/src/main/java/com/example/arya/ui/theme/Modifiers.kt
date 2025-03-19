import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.linearGradientBackground(): Modifier = this.drawBehind {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF10A1F6), Color(0xFFE9C39B)), // Use the provided colors
        start = Offset.Zero,
        end = Offset(size.width, size.height)
    )
    drawRect(brush = gradient)
}