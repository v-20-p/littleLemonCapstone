package com.example.littlelemoncapstone.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.littlelemoncapstone.R
import com.example.littlelemoncapstone.data.MenuItemRoom
import com.example.littlelemoncapstone.data.MenuViewModel
import com.example.littlelemoncapstone.navigations.Destinations
import com.example.littlelemoncapstone.ui.theme.PrimaryGreen
import com.example.littlelemoncapstone.ui.theme.PrimaryYellow
import com.example.littlelemoncapstone.ui.theme.Secondary1
import com.example.littlelemoncapstone.ui.theme.Secondary2


@Composable
fun Home(navController: NavController) {



    val viewModel: MenuViewModel = viewModel()
    val databaseMenuItems = viewModel.getAllDatabaseMenuItems().observeAsState(emptyList()).value
    val filteredMenuItems = remember {mutableStateOf<List<MenuItemRoom>>(emptyList()) }
    filteredMenuItems.value = databaseMenuItems
    val searchPhrase = remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        viewModel.fetchMenuDataIfNeeded()
    }


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            // Header
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                    Box (Modifier.weight(1f)){

                    }

                        Image(
                            painter = painterResource(R.drawable.logo__1_),
                            contentDescription = "Logo Image",
                            modifier = Modifier
                                .size(200.dp)


                        )

//
//                Spacer(modifier = Modifier.width(16.dp))

                    Box (Modifier.weight(1f),contentAlignment = Alignment.CenterEnd){
                        Image(
                            painter = painterResource(R.drawable.profile),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(80.dp)

                                .clickable {
                                    navController.navigate(Destinations.Profile.route)
                                }
                        )
                    }
            }

            // Hero section

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = PrimaryGreen)
                    .padding(8.dp),
            ) {
                Column{
                    Text(
                        text = "Little Lemon",
                        fontSize = 49.sp,
                        color = PrimaryYellow,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .background(color = PrimaryGreen)
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Left Column (Location, Description)
                Column(
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {

                    Text(
                        text = "Chicago",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 29.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(end = 8.dp),
                    )

                }
                // Image Column
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {

                    Image(
                        painter = painterResource(R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            //.fillMaxWidth(0.6f)
                            .size(150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),

                        )
                }

            }

            // Search TextField
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = PrimaryGreen)
                    .padding(bottom = 16.dp)
            ) {
                TextField(
                    value = searchPhrase.value,
                    onValueChange = { searchPhrase.value = it
                        filteredMenuItems.value = databaseMenuItems.filter {menu->
                            menu.title.contains(searchPhrase.value, ignoreCase = true)
                        }



                                    },
                    placeholder = { Text("Enter search phrase",fontSize = 15.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .height(50.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ""
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF6FCF6),
                    ) ,
                    textStyle= MaterialTheme.typography.bodyMedium,
                    singleLine = true,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 30.dp)
            ) {
                Text(
                    text = "Order for delivery!".uppercase(),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black)
            }

            var clicked by remember {mutableStateOf("") }


            LazyRow(Modifier.padding(start = 16.dp, top = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item {
                    Button(onClick = { filteredMenuItems.value = databaseMenuItems }
                        ,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = PrimaryGreen

                        ),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Text("All",fontSize = 16.sp)

                    }
                }
                databaseMenuItems.forEach {menu ->
                    item{
                        Button(onClick = { filteredMenuItems.value = databaseMenuItems.filter {
                            it.category == menu.category
                        } }
                            ,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,
                                contentColor = PrimaryGreen

                            ),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text(menu.category,fontSize = 16.sp)

                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))


            //Filtered MenuItems


            MenuItems(menuItems = filteredMenuItems.value)

        }

    }

}

@Composable
fun MenuItems(menuItems: List<MenuItemRoom>) {
    Spacer(modifier = Modifier
        .width(20.dp)
        .padding(top = 10.dp, bottom = 10.dp))
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        for (menuItem in menuItems) {
            MenuItem(item = menuItem)
        }
    }
}

@Composable
fun MenuItem(item: MenuItemRoom) {
    Spacer(modifier = Modifier.width(10.dp))
    Divider(color = Color.Gray, thickness = 1.dp)
    Spacer(modifier = Modifier.width(10.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
            Text(text = item.title, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp))
            Text(text = item.description,
                modifier = Modifier.padding(bottom = 8.dp), fontSize = 14.sp)
            Text(text = "$${item.price}", fontSize = 18.sp)
        }
        Column {
            Spacer(modifier = Modifier.width(10.dp))
            AsyncImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier.size(100.dp, 100.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

    }
}
