package com.example.arya.screens.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.zIndex
import com.example.arya.R
import com.example.arya.ui.theme.InterFontFamily
import linearGradientBackground


@Composable
fun ChatScreen(modifier: Modifier = Modifier, backgroundModifier: Modifier = Modifier) {


    Column(
        Modifier
            .fillMaxSize()
            .then(backgroundModifier)
    ) {
        ChatToolbar(
            backgroundModifier = backgroundModifier,
            displayPicture = painterResource(id = R.drawable.arya_profileavatars_sarahcarter),
            userName = "Sarah Carter",
            navigationIcon = painterResource(id = R.drawable.icon_arrow_previous_64x64),
            onNavigationClick = { /* Handle navigation click */ },
        )

        val messageText1 =
            "Hey John, let's get together and discuss the job proposal. Does Monday Work?"
        val timeText1 = "11:48 AM"

        val messageText2 = "That would be great. Yes, I will see you on Monday."
        val timeText2 = "1:54 PM"
        MessageCard(
            Modifier,
            messageText1,
            timeText1,
            backgroundColor = Color(0xFF1F94D1),
            false
        )

        MessageCard(
            Modifier,
            messageText2,
            timeText2,
            backgroundColor = Color.White,
            true
        )


        Spacer(modifier = Modifier.weight(1f))

        SendMessageBox(
            modifier = Modifier
                .fillMaxWidth(), backgroundModifier
        )
    }

}


// This composable function creates a customizable toolbar for the chat screen.
// It includes a navigation icon, a display picture, and the user's name.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatToolbar(
    backgroundModifier: Modifier,
    displayPicture: Painter,
    userName: String,
    navigationIcon: Painter,
    onNavigationClick: () -> Unit = {},
) {

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = displayPicture,
                    contentDescription = "User Display Picture",
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(15.dp))
                )
                Text(
                    text = userName,
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
            IconButton(onClick = onNavigationClick) {
                Icon(
                    painter = navigationIcon,
                    tint = Color.White,
                    contentDescription = "Navigation Icon"
                )
            }
        },
        actions = {
            // You can add more actions here (e.g., settings, profile, etc.)
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)

    )
}


@Composable
fun MessageCard(
    modifier: Modifier,
    messageText: String,
    timeText: String,
    backgroundColor: Color,
    isDelivered: Boolean = false,
) {
    val textColor =
        if (backgroundColor == Color.White) Color.Black else Color.White // Determine text color

    Row( // Use Row to control alignment
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start // Align to the start
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = messageText,
                    style = TextStyle(
                        color = textColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = timeText,
                        style = TextStyle(
                            color = if (isDelivered) Color(0xFF42A5F5) else textColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        ),
                    )

                    if (isDelivered) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_chat_read_64x64),
                            contentDescription = "Delivered",
                            tint = Color(0xFF42A5F5), // Blue checkmark
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
fun SendMessageBox(modifier: Modifier, backgroundModifier: Modifier) {
    var text by remember { mutableStateOf("") }
    var showAttachmentOptions by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 70.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                showAttachmentOptions = !showAttachmentOptions
            }) { // Toggle attachment options
                Icon(
                    painter = painterResource(id = R.drawable.icon_plus_64x64), // Replace with your plus icon
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = TextFieldDefaults.colors(),
                    textStyle = LocalTextStyle.current.copy(color = Color.White),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Message",
                            color = Color.Black.copy(alpha = 0.7f)
                        )
                    },
                    trailingIcon = {
                        if (text.trim().isNotEmpty()) {
                            IconButton(onClick = {}) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_sendmessage_64x64),
                                    contentDescription = "Send",
                                    tint = Color.White
                                )
                            }
                        }
                    },
                    shape = RoundedCornerShape(30.dp) // Apply rounded corners
                )
            }

            AnimatedVisibility(
                visible = showAttachmentOptions,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.zIndex(1f) // Ensure it's on top
            ) {
                AttachmentOptionsOverlay()
            }

        }

    }
}


@Composable
fun AttachmentOptionsOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .blur(radius = 10.dp) // Apply blur effect
            .clickable { /* Prevent clicks from passing through */ },
        contentAlignment = Alignment.BottomStart
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
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