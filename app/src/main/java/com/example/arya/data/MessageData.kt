package com.example.arya.data

data class MessageData(
    val message: String,
    val time: String,
    val isSender: Boolean // true if sender, false if receiver
)