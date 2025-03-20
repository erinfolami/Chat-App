package com.example.arya.screens.chat.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arya.R
import com.example.arya.data.MessageData
import com.example.arya.data.sampleMessages

// Displays a single message item with different styles for sender and receiver.
// Includes message text, timestamp, and read status for non-senders.
@Composable
fun MessageItem(messageData: MessageData) {
    val backgroundColor = if (messageData.isSender) Color(0xFF1F94D1) else Color.White
    val textColor = if (messageData.isSender) Color.White else Color.Black
    val alignment = if (messageData.isSender) Arrangement.End else Arrangement.Start
    val paddingModifier =
        if (messageData.isSender) Modifier.padding(end = 70.dp) else Modifier.padding(start = 70.dp)
    val timeTextColor =
        if (messageData.isSender) Color.White else Color(0xFF01A6FC)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .then(paddingModifier),
        horizontalArrangement = alignment
    ) {
        Card(
            modifier = Modifier.wrapContentSize(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = messageData.message,
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = messageData.time,
                        style = TextStyle(
                            color = timeTextColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        ),
                    )

                    if (!messageData.isSender) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat_read),
                            contentDescription = stringResource(R.string.delivered),
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
fun PreviewMessageItem() {
    MessageItem(
        messageData = sampleMessages[0]
    )
}