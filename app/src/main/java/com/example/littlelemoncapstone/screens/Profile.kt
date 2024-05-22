package com.example.littlelemoncapstone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemoncapstone.R
import com.example.littlelemoncapstone.datastore.DataStoreManager
import com.example.littlelemoncapstone.navigations.Destinations
import com.example.littlelemoncapstone.ui.theme.PrimaryGreen
import com.example.littlelemoncapstone.ui.theme.PrimaryYellow
import kotlinx.coroutines.launch


@Preview(showBackground = true)
@Composable
fun Profile(){
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }
    val coroutineScope = rememberCoroutineScope()
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    LaunchedEffect(dataStoreManager) {
        dataStoreManager.firstNameFlow.collect { value ->
            firstName = value
        }
    }
    LaunchedEffect(dataStoreManager) {
    dataStoreManager.lastNameFlow.collect { value ->
        lastName = value
    }}
    LaunchedEffect(dataStoreManager) {
    dataStoreManager.emailFlow.collect { value ->
        email = value
    }}

    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()

            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Spacer(modifier = Modifier.size(10.dp))

        Row(Modifier.fillMaxWidth(0.6f)) {
            Image(painter = painterResource(id = R.drawable.logo__1_),
                contentDescription = "Little Lemon Logo")
        }
        Spacer(modifier = Modifier.height(100.dp))


        Column(Modifier.padding(20.dp), Arrangement.spacedBy(40.dp)){
            Text(
                text = "Personal Information",
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            OutlinedTextField(
                value = firstName,
                onValueChange = {},
                enabled = false,
                label = { Text(text = "First Name") },
                singleLine = true,

                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = PrimaryGreen,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = {},
                enabled = false,
                label = { Text(text = "Last Name") },
                singleLine = true,

                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = PrimaryGreen,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                onValueChange = {},
                enabled = false,
                label = { Text(text = "Email") },
                singleLine = true,

                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = PrimaryGreen,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(40.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        dataStoreManager.setIsLogin(false)
                        dataStoreManager.setFirstName("")
                        dataStoreManager.setLastName("")
                        dataStoreManager.setEmail("")
                    }



                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        1.dp,
                        Color.Red.copy(alpha = 0.3f), RoundedCornerShape(10.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryYellow,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
//
            ) {
                Text(text = "Log Out")
            }

            }




        }
    }





