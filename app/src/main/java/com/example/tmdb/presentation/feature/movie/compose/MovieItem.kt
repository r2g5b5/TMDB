package com.example.tmdb.presentation.feature.movie.compose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.crypto.common.Constants
import com.example.tmdb.R
import com.example.tmdb.domain.model.movie.Movie


@Composable
fun MovieItem(
    movie: Movie,
) {
    val context=LocalContext.current
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(Constants.BASE_IMAGE_URL + movie.backdrop_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(28.dp))
            .clickable {
                Toast.makeText(context,movie.title,Toast.LENGTH_SHORT).show()
            }
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .height(154.dp)
                .width(119.dp)
                .clip(RoundedCornerShape(12.dp)),
            painter = if (imageState.painter == null) {
                painterResource(id = R.drawable.no_image)
            } else {
                imageState.painter!!
            },
            contentDescription = movie.title,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(6.dp))

        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp)
                .fillMaxWidth(),
            text = movie.title,
            fontSize = 14.sp,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}





















