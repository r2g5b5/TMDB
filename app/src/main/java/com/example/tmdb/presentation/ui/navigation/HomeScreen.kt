package com.example.tmdb.presentation.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tmdb.R
import com.example.tmdb.presentation.feature.movie.GetMoviesViewModel
import com.example.tmdb.presentation.feature.movie.compose.MoviesScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val movieListViewModel = hiltViewModel<GetMoviesViewModel>()
    val navController = rememberNavController()
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Discover",
                    fontSize = 24.sp,
                    color = Color(0xFFFF3D00),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            actions = {

                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(painterResource(id = R.drawable.bazaar), contentDescription = null,tint = Color.Unspecified)

                }
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            NavHost(
                navController = navController,
                startDestination = Destinations.MoviesScreen.route
            ) {
                composable(Destinations.MoviesScreen.route) {
                    MoviesScreen(
                        onEvent = movieListViewModel::onEvent
                    )
                }
            }

        }
    }
}













