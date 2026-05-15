package com.example.ksheera_sagara.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.ksheera_sagara.data.AppPrefs

@Composable
fun LockScreen(
    onUnlock: () -> Unit
) {

    val context = LocalContext.current
    val prefs = remember { AppPrefs(context) }

    val savedPin = prefs.getPin() ?: ""

    var inputPin by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🔒 App Locked",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = inputPin,
            onValueChange = {
                inputPin = it
                error = ""
            },
            label = { Text("Enter PIN") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (error.isNotEmpty()) {
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (inputPin == savedPin) {
                    onUnlock()
                } else {
                    error = "Wrong PIN"
                }
            }
        ) {
            Text("Unlock")
        }
    }
}