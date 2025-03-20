package com.example.arya.screens.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.zIndex
import com.example.arya.R
import com.example.arya.ui.theme.InterFontFamily
import linearGradientBackground


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

// This composable function creates a customizable toolbar for the chat screen.
// It includes a navigation icon, a display picture, and the user's name.
// This composable function creates a customizable toolbar for the chat screen.
// It includes a navigation icon, a display picture, and the user's name.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatToolbar(
    onBackPressed: () -> Unit = {},
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            // navigationIconContentColor = Color.White,
        ),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.arya_profileavatars_sarahcarter),
                    contentDescription = "User Display Picture",
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(15.dp))
                )
                Text(
                    text = "Sarah Carter",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = InterFontFamily
                    ),
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_previous_64x64),
                    tint = Color.White,
                    contentDescription = "Navigation Icon"
                )
            }
        },
        actions = {
            // You can add more actions here (e.g., settings, profile, etc.)
        },

        )
}

data class MessageData(
    val message: String,
    val time: String,
    val isSender: Boolean // true if sender, false if receiver
)


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


@Composable
fun SendMessageBox(
    modifier: Modifier,
    showAttach: () -> Unit,
) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
    ) {
        var text by remember { mutableStateOf("") }
        val interactionSource = remember { MutableInteractionSource() }

        Image(
            painter = painterResource(R.drawable.icon_plus_64x64),
            contentDescription = "",
            modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically)
                .size(16.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                ) {
                    showAttach()
                },
        )

        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(
                    "write message",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            textStyle = TextStyle(color = White),
            shape = RoundedCornerShape(32.dp),
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = Color.Transparent.copy(alpha = 0.1F),
                unfocusedContainerColor = Color.Transparent.copy(alpha = 0.1F),
                cursorColor = White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            maxLines = 2,
            // enabled = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Send,
            ),
            keyboardActions = KeyboardActions(onSend = {
                //send(text).also { text = "" }
            }),
            trailingIcon = {
                AnimatedVisibility(
                    visible = text.isNotBlank(),
                    enter = scaleIn(),
                    exit = scaleOut(),
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_sendmessage_64x64),
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 8.dp)
                            .clickable {
                                //send(text).also { text = "" }
                            }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Composable
fun AttachmentOptionsOverlay(
    modifier: Modifier,
    closeAttach: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        // Attachment options content
        Column(
            modifier = Modifier
                .clickable { closeAttach() }
                .padding(16.dp)
                .align(Alignment.BottomStart) // Align to bottom start
        ) {
            AttachmentOption(iconId = R.drawable.icon_camera_64x64, text = "Camera")
            AttachmentOption(iconId = R.drawable.icon_photos_64x64, text = "Photos")
            AttachmentOption(iconId = R.drawable.icon_files_64x64, text = "Files")
            AttachmentOption(iconId = R.drawable.icon_audio_64x64, text = "Audio")
        }
    }
}

@Composable
fun AttachmentOption(iconId: Int, text: String) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f))
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
fun DefaultPreview() {
    val backgroundModifier = Modifier.linearGradientBackground()

    ChatScreen(backgroundModifier)
}