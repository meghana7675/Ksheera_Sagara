package com.example.ksheera_sagara.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ksheera_sagara.R
import com.example.ksheera_sagara.viewmodel.MainViewModel

@Composable
fun AddCowScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    var name by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // =========================
        // TITLE
        // =========================

        Text(
            text = "Add Cow",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        // =========================
        // COW IMAGE
        // =========================

        Image(
            painter = painterResource(id = R.drawable.cow2),
            contentDescription = "Cow Image",

            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(20.dp)),

            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        // =========================
        // NAME
        // =========================

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },

            label = {
                Text("Cow Name")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // =========================
        // BREED
        // =========================

        OutlinedTextField(
            value = breed,
            onValueChange = {
                breed = it
            },

            label = {
                Text("Breed")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // =========================
        // AGE
        // =========================

        OutlinedTextField(
            value = age,
            onValueChange = {
                age = it
            },

            label = {
                Text("Age")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // =========================
        // SAVE BUTTON
        // =========================

        Button(
            onClick = {

                viewModel.addCow(
                    name = name,
                    breed = breed,
                    age = age,
                    photoUri = "cow1"
                )

                navController.popBackStack()
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {

            Text("Save Cow")
        }
    }
}