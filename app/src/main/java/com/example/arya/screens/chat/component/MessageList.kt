package com.example.arya.screens.chat.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.arya.data.MessageData

@Composable
fun MessageList(messages: List<MessageData>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(messages) { messageData ->
            MessageItem(messageData = messageData)
        }
    }
}