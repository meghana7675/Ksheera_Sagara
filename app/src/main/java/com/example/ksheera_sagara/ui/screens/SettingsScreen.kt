package com.example.ksheera_sagara.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ksheera_sagara.data.AppPrefs

@Composable
fun SettingsScreen() {

    val context = LocalContext.current
    val prefs = remember { AppPrefs(context) }

    var language by remember { mutableStateOf(prefs.getLanguage()) }
    var fontSize by remember { mutableStateOf(prefs.getFontSize()) }
    var milkPrice by remember { mutableStateOf(prefs.getMilkPrice().toString()) }
    var lockEnabled by remember { mutableStateOf(prefs.isLockEnabled()) }
    var pin by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text("Settings", style = MaterialTheme.typography.headlineMedium)

        // 🌐 Language
        Text("Language")
        Row {
            RadioButton(
                selected = language == "English",
                onClick = {
                    language = "English"
                    prefs.setLanguage(language)
                }
            )
            Text("English")

            Spacer(modifier = Modifier.width(10.dp))

            RadioButton(
                selected = language == "Kannada",
                onClick = {
                    language = "Kannada"
                    prefs.setLanguage(language)
                }
            )
            Text("Kannada")
        }

        // 🔠 Font Size
        Text("Font Size: ${fontSize.toInt()}")
        Slider(
            value = fontSize,
            onValueChange = {
                fontSize = it
                prefs.setFontSize(it)
            },
            valueRange = 12f..24f
        )

        // 🥛 Milk Price
        Text("Milk Price (per liter)")
        OutlinedTextField(
            value = milkPrice,
            onValueChange = {
                milkPrice = it
                it.toFloatOrNull()?.let { price ->
                    prefs.setMilkPrice(price)
                }
            },
            label = { Text("Enter price") }
        )

        // 🔒 App Lock
        Row {
            Text("App Lock")
            Spacer(modifier = Modifier.width(10.dp))

            Switch(
                checked = lockEnabled,
                onCheckedChange = {
                    lockEnabled = it
                    prefs.setLockEnabled(it)
                }
            )
        }

        if (lockEnabled) {
            OutlinedTextField(
                value = pin,
                onValueChange = {
                    pin = it
                    if (it.length == 4) {
                        prefs.setPin(it)
                    }
                },
                label = { Text("Set 4-digit PIN") }
            )
        }

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Settings")
        }
    }
}