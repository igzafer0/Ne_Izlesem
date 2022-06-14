package com.igzafer.neizlesem.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.igzafer.neizlesem.BuildConfig
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.domain.model.MoviesModel


@BindingAdapter("imageUrl")
fun downloadImage(imageView: ImageView, url: String?) {
    url?.let {

        imageView.load(BuildConfig.PHOTO_URL + url) {
            crossfade(false)
                .error(R.drawable.ic_popcorn)
                .placeholder(R.drawable.loading)
        }

    }

}

@BindingAdapter("imageUrlHighRes")
fun downloadImageHighRes(imageView: ImageView, url: String?) {
    url?.let {
        imageView.load(BuildConfig.HIGH_RES_PHOTO_URL + url) {
            crossfade(false)
                .error(R.drawable.ic_popcorn)
                .placeholder(R.drawable.loading)
        }

    }
}

