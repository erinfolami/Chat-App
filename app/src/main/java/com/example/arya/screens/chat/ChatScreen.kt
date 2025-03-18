package com.example.arya.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import com.example.arya.R


@Composable
fun ChatScreen(){
    ChatScreen()
}

// This composable function creates a customizable toolbar for the chat screen.
// It includes a navigation icon, a display picture, and the user's name.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatToolbar(
    displayPicture: Painter, // Profile image
    userName: String,        // User name
    navigationIcon: Painter, // Navigation icon
    onNavigationClick: () -> Unit = {}, // Optional navigation click action
) {
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
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontFamily = FontFamily.Default
                    )
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    painter = navigationIcon,
                    contentDescription = "Navigation Icon"
                )
            }
        },
        actions = {
            // You can add more actions here (e.g., settings, profile, etc.)
        },
        modifier = Modifier.fillMaxWidth()
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewChatToolbar() {
    ChatToolbar(
        displayPicture = painterResource(id = R.drawable.arya_profileavatars_sarahcarter),
        userName = "Sarah Carter",
        navigationIcon = painterResource(id = R.drawable.icon_arrow_previous),
        onNavigationClick = { /* Handle navigation click */ },
    )
}
