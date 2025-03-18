package com.example.arya.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import com.example.arya.R
import com.example.arya.ui.theme.InterFontFamily


@Composable
fun ChatScreen(modifier: Modifier = Modifier) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        ChatToolbar(
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
            backgroundColor = Color.Black,
            false
        )
        Spacer(modifier = Modifier.height(16.dp))
        MessageCard(
            Modifier,
            messageText2,
            timeText2,
            backgroundColor = Color.White,
            true
        )
    }
}

// This composable function creates a customizable toolbar for the chat screen.
// It includes a navigation icon, a display picture, and the user's name.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatToolbar(
    displayPicture: Painter,
    userName: String,
    navigationIcon: Painter,
    onNavigationClick: () -> Unit = {},
) {
    2
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = displayPicture,
                    contentDescription = "User Display Picture",
                    modifier = Modifier.wrapContentSize()
                )
                Text(
                    text = userName,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = InterFontFamily
                    ),
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
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF42A5F5))

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
                Row(modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.End) {
                    Text(
                        text = timeText,
                        style = TextStyle(
                            color = textColor,
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatScreen()
}