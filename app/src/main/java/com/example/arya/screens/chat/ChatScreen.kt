package com.example.arya.screens.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arya.R
import com.example.arya.data.MessageData
import com.example.arya.screens.chat.component.ChatToolbar
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
        var showAttachmentOptions by remember { mutableStateOf(false) } // Add state for overlay visibility
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


                val messages = listOf(
                    MessageData(
                        message = "Hey John, let's get together and discuss the job proposal. Does Monday Work?",
                        time = "11:48 AM",
                        isSender = true,
                    ),
                    MessageData(
                        message = "That would be great. Yes, I will see you on Monday.",
                        time = "11:54 AM",
                        isSender = false,
                    ),
                )

                MessageList(messages = messages)


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


@Composable
fun MessageList(messages: List<MessageData>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(messages) { messageData ->
            MessageItem(messageData = messageData)
        }
    }
}


@Composable
fun MessageItem(messageData: MessageData) {
    val backgroundColor = if (messageData.isSender) Color(0xFF1F94D1) else Color.White
    val textColor = if (messageData.isSender) Color.White else Color.Black
    val alignment = if (messageData.isSender) Arrangement.End else Arrangement.Start
    val paddingModifier =
        if (messageData.isSender) Modifier.padding(end = 70.dp) else Modifier.padding(start = 70.dp)
    val timeTextColor =
        if (messageData.isSender) Color.White else Color(0xFF02A6FC)  // Determine time text color

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .then(paddingModifier),
        horizontalArrangement = alignment
    ) {
        Card(
            modifier = Modifier.wrapContentWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = messageData.message,
                    style = TextStyle(
                        color = textColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = messageData.time,
                        style = TextStyle(
                            color = timeTextColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        ),
                    )

                    if (!messageData.isSender) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_chat_read_64x64),
                            contentDescription = "Delivered",
                            tint = Color(0xFF42A5F5),
                            modifier = Modifier
                                .size(16.dp)
                                .padding(start = 4.dp)
                        )
                    }
                }
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ARYATheme {
        ChatScreen()
        //  GradientScaffoldScreen()
    }
    //    val backgroundModifier = Modifier.linearGradientBackground()


}
