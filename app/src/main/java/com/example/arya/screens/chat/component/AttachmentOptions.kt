package com.example.arya.screens.chat.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
            .clickable { closeAttach() }
            .padding(bottom = 60.dp, start = 20.dp)
    ) {
        AttachmentOptionItem(
            iconId = R.drawable.ic_camera,
            text = stringResource(R.string.camera),
            AlloyGradient
        )
        AttachmentOptionItem(
            iconId = R.drawable.ic_photos,
            text = stringResource(R.string.photos),
            SunshineGradient
        )
        AttachmentOptionItem(
            iconId = R.drawable.ic_files,
            text = stringResource(R.string.files),
            GreenBeachGradient
        )
        AttachmentOptionItem(
            iconId = R.drawable.ic_audio,
            text = stringResource(R.string.audio),
            PlumGradient
        )
    }

}

// UI item for an individual attachment option with an icon and label.
@Composable
fun AttachmentOptionItem(iconId: Int, text: String, color: Brush) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable {},
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(iconId),
                contentDescription = "Camera icon",
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = Color.White, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAttachmentOptionsOverlay() {
    AttachmentOptionsOverlay(modifier = Modifier, closeAttach = {})
}