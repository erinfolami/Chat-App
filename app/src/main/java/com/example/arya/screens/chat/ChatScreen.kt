package com.example.arya.screens.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.arya.data.MessageData
import com.example.arya.data.sampleMessages
import com.example.arya.screens.chat.component.AttachmentOptionsOverlay
import com.example.arya.screens.chat.component.ChatToolbar
import com.example.arya.screens.chat.component.MessageList
import com.example.arya.screens.chat.component.SendMessageBox
import com.example.arya.ui.theme.ARYATheme
import com.example.arya.ui.theme.BackgroundGradient
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials


@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGradient)
    ) {
        val hazeState = remember { HazeState() }
        var showAttachmentOptions by remember { mutableStateOf(false) }
        Scaffold(
            containerColor = Color.Transparent,
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .hazeSource(hazeState),
            topBar = {
                ChatToolbar()
            },
            bottomBar = {
                SendMessageBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    showAttach = {
                        showAttachmentOptions = true
                    }
                )
            }

        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()

            ) {
                MessageList(messages = sampleMessages)
            }


        }

        AnimatedVisibility(
            showAttachmentOptions,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500)),
        ) {
            AttachmentOptionsOverlay(
                modifier = Modifier
                    .fillMaxSize()
                    .hazeEffect(state = hazeState, style = HazeMaterials.ultraThin()),
                closeAttach = { showAttachmentOptions = false },
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ARYATheme {
        ChatScreen()
    }


}
