package com.example.arya.screens.chat.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arya.R


// Chat toolbar with back navigation and user profile display.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatToolbar(
    onBackPressed: () -> Unit = {},
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        ),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.arya_profileavatars_sarahcarter),
                    contentDescription = stringResource(R.string.user_display_picture),
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(15.dp))
                )
                Text(
                    text = stringResource(R.string.sarah_carter),
                    style = MaterialTheme.typography.titleLarge,
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
                    contentDescription = stringResource(R.string.navigation_icon)
                )
            }
        },
        actions = {},

        )
}


@Preview(showBackground = true)
@Composable
fun PreviewChatToolbar() {
    ChatToolbar()
}