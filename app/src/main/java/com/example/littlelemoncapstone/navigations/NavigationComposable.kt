package com.example.littlelemoncapstone.navigations

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemoncapstone.datastore.DataStoreManager
import com.example.littlelemoncapstone.screens.Home
import com.example.littlelemoncapstone.screens.Onboarding
import com.example.littlelemoncapstone.screens.Profile


@Composable
fun Navigation( navController: NavHostController){
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }
    var isLogin by remember { mutableStateOf(false) }

    LaunchedEffect(dataStoreManager) {
        dataStoreManager.isLoginFlow.collect { value ->
            isLogin = value
        }
    }


    var startDestination = Destinations.Onboarding.route
    if (isLogin) {
        startDestination = Destinations.Home.route
    }


    NavHost(navController = navController, startDestination = startDestination){
        composable(Destinations.Onboarding.route){
            Onboarding(navController)
        }
        composable(Destinations.Home.route){
            Home(navController = navController)
        }
        composable(Destinations.Profile.route){
            Profile()
        }
    }

}