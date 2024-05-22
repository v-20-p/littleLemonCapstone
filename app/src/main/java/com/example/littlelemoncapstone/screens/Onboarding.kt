package com.example.littlelemoncapstone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.TextFieldDefaults
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
import androidx.navigation.NavHostController
import com.example.littlelemoncapstone.R
import com.example.littlelemoncapstone.datastore.DataStoreManager
import com.example.littlelemoncapstone.navigations.Destinations
import com.example.littlelemoncapstone.ui.theme.PrimaryGreen
import com.example.littlelemoncapstone.ui.theme.PrimaryYellow
import kotlinx.coroutines.launch


@Composable
fun Onboarding(navController: NavHostController) {

    val context = LocalContext.current

    val dataStoreManager = remember { DataStoreManager(context) }
    val coroutineScope = rememberCoroutineScope()
    val validateState = remember { mutableStateOf(false) }



    val firstName = remember {
        mutableStateOf("")
    }

    val lastName = remember {
        mutableStateOf("")
    }

    val email = remember {
        mutableStateOf("")
    }

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
        Row(modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .background(PrimaryGreen),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Let's get to know you",
                fontSize = 30.sp,

                color = Color.White
            )

        }
        Column(Modifier.padding(10.dp),Arrangement.spacedBy(40.dp)){
            Text(
                text = "Personal Information",
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstName.value,

                onValueChange = {
                    firstName.value = it
                    validateState.value = false
                },
                label = { Text(text = "First Name") },
                singleLine = true,
                placeholder = { Text(text = "John") },
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = PrimaryGreen,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = if (validateState.value) Color.Red else Color.Gray,
                    focusedBorderColor = if (validateState.value) Color.Red else Color.Gray,

                    ),
            )

            OutlinedTextField(
                value = lastName.value,
                onValueChange = {
                    lastName.value = it
                    validateState.value = false
                },
                label = { Text(text = "Last Name") },
                singleLine = true,
                placeholder = { Text(text = "Doe") },
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = PrimaryGreen,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = if (validateState.value) Color.Red else Color.Gray,
                    focusedBorderColor = if (validateState.value) Color.Red else Color.Gray,

                    ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                    validateState.value = false
                },
                label = { Text(text = "Email") },
                singleLine = true,
                placeholder = { Text(text = "johndoe@gmail.com") },
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = PrimaryGreen,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = if (validateState.value) Color.Red else Color.Gray,
                    focusedBorderColor = if (validateState.value) Color.Red else Color.Gray,

                    ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(40.dp))
            if (validateState.value){
                Text(text = "Please fill all the fields",
                    color = Color.Red,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    )
            }



            Button(
                onClick = {
                    if (firstName.value.isNotBlank() && lastName.value.isNotBlank() && email.value.isNotBlank()){
                        coroutineScope.launch {
                            dataStoreManager.setIsLogin(true)
                            dataStoreManager.setFirstName(firstName.value)
                            dataStoreManager.setLastName(lastName.value)
                            dataStoreManager.setEmail(email.value)
                        }

                    }else{
                        validateState.value = true

                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp).border(1.dp,Color.Red.copy(alpha = 0.3f) , RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryYellow,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
//
            ) {
                Text(text = "Register")
            }
        }
    }



}