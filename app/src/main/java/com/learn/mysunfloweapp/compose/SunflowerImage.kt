package com.learn.mysunfloweapp.compose

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.RequestBuilderTransform

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SunflowerImage(
    modifier: Modifier = Modifier,
    model: Any?,
    contentDescription: String?,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    requestBuilderTransform: RequestBuilderTransform<Drawable> = { it },
) {

    GlideImage(
        modifier = modifier,
        model = model,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        requestBuilderTransform = requestBuilderTransform,
        contentDescription = ""
    )

}