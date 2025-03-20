package com.example.arya.screens.chat.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arya.R
import com.example.arya.ui.theme.White

//Composable for the message input box with attachment icon and send button.
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

@Preview(showBackground = true)
@Composable
fun PreviewSendMessageBox() {
    SendMessageBox(modifier = Modifier, showAttach = {})
}