package com.example.tmdb.presentation.feature.movie.compose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.tmdb.data.remote.retrofit.api_request.PageRequest
import com.example.tmdb.domain.model.movie.Movie
import com.example.tmdb.presentation.feature.movie.GetMoviesViewModel

@Composable
fun MoviesScreen(
    navController: NavController,
    listViewModel: GetMoviesViewModel = hiltViewModel(),
) {
    val listUiState = listViewModel.uiState
    val context = LocalContext.current
    val list = remember { mutableStateOf<List<Movie>>(arrayListOf()) }



    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        columns = GridCells.Fixed(2)
    ) {

        items(list.value) {
            Column(
                modifier = Modifier
                    .height(250.dp)
                    .width(200.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .height(200.dp),
                    painter = rememberAsyncImagePainter(it.backdrop_path),
                    contentDescription = it.title,
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = it.title,
                    fontWeight = FontWeight.SemiBold
                )

            }
            Spacer(modifier = Modifier.height(8.dp))
        }

    }

    GetList(viewModel = listViewModel)


    listUiState.value.errors?.let {
        LaunchedEffect(Unit) {
            it.forEach {
                Toast.makeText(context, it.description, Toast.LENGTH_SHORT).show()
            }
        }
    }


    listUiState.value.success?.let {
        list.value = it
    }


}

@Composable
fun GetList(viewModel: GetMoviesViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getMovies(PageRequest())
    }
}


@Composable
fun GridItem(item: Movie) {


}