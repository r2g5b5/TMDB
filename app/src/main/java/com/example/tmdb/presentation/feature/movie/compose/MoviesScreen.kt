package com.example.tmdb.presentation.feature.movie.compose

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdb.R
import com.example.tmdb.presentation.feature.movie.GetMoviesViewModel
import com.example.tmdb.presentation.feature.movie.MovieListUiEvent


@Composable
fun MoviesScreen(
    listViewModel: GetMoviesViewModel = hiltViewModel(),
    onEvent: (MovieListUiEvent) -> Unit,
) {
    val context=LocalContext.current
    val movieListState = listViewModel.movieListState.collectAsState().value
    val isConnected = isNetworkAvailable(context)

    var showErrorScreen by remember { mutableStateOf(!isConnected) }


    if (showErrorScreen) {
        NoInternetConnectionScreen(onRetry = {
            if (isNetworkAvailable(context)) {
                showErrorScreen = false
                onEvent(MovieListUiEvent.Retry)
            }
        })

    } else if (movieListState.upcomingMovieList.isEmpty() && movieListState.isLoading) {
        LoadingScreen()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(movieListState.upcomingMovieList.size) { index ->
                MovieItem(
                    movie = movieListState.upcomingMovieList[index],
                )
                Spacer(modifier = Modifier.height(16.dp))

                    if (index >= movieListState.upcomingMovieList.size - 1 && !movieListState.isLoading) {
                        onEvent(MovieListUiEvent.Paginate)

                    }

            }
        }
    }
}


@Composable
fun NoInternetConnectionScreen(onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(150.dp),
                painter = painterResource(id = R.drawable.error),
                contentDescription = "No Internet"
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Connection glitch",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Text(
                text = "Seems like there's an internet connection problem.",
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onRetry) {

                Text(text = "Retry")

            }
        }
    }
}


@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.bazaar),
                contentDescription = "Your Image",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(color = Color.Green)
        }
    }
}


fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}
