package com.mdoc.gravatardetailsdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mdoc.gravatarapi.PlaceHolderType
import com.mdoc.gravatarapi.getPublicPhoto

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserIdInputField() {
    var emailId by remember {
        mutableStateOf("")
    }

    var imageURL by remember {
        mutableStateOf("")
    }

    var isError by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        Row(
            Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(

                value = emailId,
                onValueChange = {
                    emailId = it
                    if (it.isNotEmpty())
                        isError = false
                },
                singleLine = true,
                isError = isError,
                trailingIcon = {
                    if (isError)
                        Icon(
                            Icons.Filled.Warning,
                            "",
                            tint = MaterialTheme.colorScheme.error
                        )
                },
                supportingText = {
                    if (isError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Email cannot be empty",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                label = { Text(text = "Enter Email ID") },
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (emailId.isNotEmpty()) {
                    isError = false
                    run {

                        // THIS IS WHERE THE MAGIC HAPPENS!!!
                        imageURL =
                            getPublicPhoto(emailId, 600, defaultType = PlaceHolderType.IDENTICON)

                    }
                } else {
                    isError = true
                }
                emailId = ""
                keyboardController?.hide()
            }) {
                Text(text = "Search")
            }

        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageURL)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.clip(CircleShape)
        )
    }

}