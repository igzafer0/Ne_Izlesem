package com.igzafer.neizlesem.presentation.adapter

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import coil.load
import com.igzafer.neizlesem.BuildConfig
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.domain.model.MovieImagesModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController


class SliderAdapter(
    var context: Context, var images: List<MovieImagesModel>?,
) : PagerAdapter() {
    var layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return images?.size ?: 1
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as View
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //            view = layoutInflater.inflate(R.layout.youtube_player, container, false)
//            val xd = view.findViewById<View>(R.id.youtube_player) as YouTubePlayerView
//            xd.enableAutomaticInitialization = false
//            xd.visibility=View.INVISIBLE
//            xd.enterFullScreen()
//            val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
//
//                override fun onReady(youTubePlayer: YouTubePlayer) {
//                    xd.visibility=View.VISIBLE
//                    val defaultPlayerUiController =
//                        DefaultPlayerUiController(xd, youTubePlayer)
//                    defaultPlayerUiController.showUi(false)
//
//                    xd.setCustomPlayerUi(defaultPlayerUiController.rootView)
//                    youTubePlayer.mute()
//                    youTubePlayer.loadVideo(
//                        "S0Q4gqBUs7c", 0f,
//                    );
//
//
//                }
//
//
//            }
//
//            val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
//            xd.initialize(listener, options)
//
//            container.addView(xd)

        val view: View = layoutInflater.inflate(R.layout.slider_row_style, container, false)
        val imageView = view.findViewById<View>(R.id.im_poster_big) as ImageView
        if (images == null) {
            imageView.setImageResource(R.drawable.ic_popcorn)
            container.addView(imageView)
        } else {
            val posterPath = BuildConfig.HIGH_RES_PHOTO_URL + images!![position].filePath
            imageView.load(posterPath)
            container.addView(imageView)


        }

        return view
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}