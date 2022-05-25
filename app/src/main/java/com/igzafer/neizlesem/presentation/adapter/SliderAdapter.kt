package com.igzafer.neizlesem.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.igzafer.neizlesem.BuildConfig
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.data.model.movie.movie_images.Backdrop

class SliderAdapter(var context: Context, var images: List<Backdrop>?) : PagerAdapter() {
    var layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return images?.size ?: 1
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as ImageView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(R.layout.slider_row_style, container, false)
        val imageView = view.findViewById<View>(R.id.im_poster_big) as ImageView
        if (images == null) {
            imageView.setImageResource(R.mipmap.ic_launcher_round)
            container.addView(imageView)
        } else {
            val posterPath = BuildConfig.HIGH_RES_PHOTO_URL + images!![position].filePath
            Glide.with(imageView.context).load(posterPath)
                .thumbnail(Glide.with(imageView.context).load(R.drawable.loading))
                .into(imageView)
            container.addView(imageView)

        }

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }
}