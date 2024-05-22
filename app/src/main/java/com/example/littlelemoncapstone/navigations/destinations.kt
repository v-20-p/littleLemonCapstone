package com.example.littlelemoncapstone.navigations

 sealed class Destinations(
    val route: String,

){
     data object Home: Destinations("home")
     data object Onboarding: Destinations("Onboarding")
     data object Profile: Destinations("Profile")

}
