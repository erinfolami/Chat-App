package com.example.arya.screens.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arya.R
import com.example.arya.ui.theme.AlloyGradient
import com.example.arya.ui.theme.GreenBeachGradient
import com.example.arya.ui.theme.PlumGradient
import com.example.arya.ui.theme.SunshineGradient

// Overlay for attachment options with close on click functionality.
@Composable
fun AttachmentOptionsOverlay(
    modifier: Modifier,
    closeAttach: () -> Unit
) {
    Box(
        modifier = modifier.padding(bottom = 80.dp, start = 24.dp, end = 24.dp, top = 32.dp)
    ) {
        // Attachment options content
        Column(
            modifier = Modifier
                .clickable { closeAttach() }
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            AttachmentOptionItem(iconId = R.drawable.icon_camera_64x64, text = stringResource(R.string.camera), AlloyGradient)
            AttachmentOptionItem(iconId = R.drawable.icon_photos_64x64, text = stringResource(R.string.photos), SunshineGradient)
            AttachmentOptionItem(iconId = R.drawable.icon_files_64x64, text = stringResource(R.string.files), GreenBeachGradient)
            AttachmentOptionItem(iconId = R.drawable.icon_audio_64x64, text = stringResource(R.string.audio), PlumGradient)
        }
    }
}

// UI item for an individual attachment option with an icon and label.
@Composable
fun AttachmentOptionItem(iconId: Int, text: String,color: Brush) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = text,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAttachmentOptionsOverlay() {
    AttachmentOptionsOverlay(modifier = Modifier, closeAttach = {})
}