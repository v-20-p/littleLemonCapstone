package com.example.littlelemoncapstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.littlelemoncapstone.navigations.Navigation
import com.example.littlelemoncapstone.ui.theme.LittleLemonCapstoneTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

class MainActivity : ComponentActivity() {
    val client = HttpClient(Android){

        install(ContentNegotiation){
            json(contentType = ContentType("application", "json"))
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            LittleLemonCapstoneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   innerPadding.calculateTopPadding()
                    Navigation(navController)
                }
            }
        }
    }
}



