package com.igzafer.neizlesem.presentation.adapter.Actors

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.igzafer.neizlesem.data.model.actor.ActorsModel
import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.databinding.ActorsRowStyleBinding
import com.igzafer.neizlesem.databinding.MoviesRowStyleBinding
import com.igzafer.neizlesem.presentation.adapter.Movie.NowPlayingMoviesRowAdapter

class PopularActorsRowAdapter :
    PagingDataAdapter<ActorsModel, PopularActorsRowAdapter.ViewHolder>(DiffUtilCallBack()) {


    inner class ViewHolder(val binding: ActorsRowStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ActorsModel) {
            Log.d("winter", data.id.toString())
            if (data.profilePath == null) {
                binding.circleActorAvatar.visibility = View.GONE
            } else {
                val posterPath = "https://image.tmdb.org/t/p/w500" + data.profilePath
                Glide.with(binding.circleActorAvatar.context).load(posterPath)
                    .into(binding.circleActorAvatar)
            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
             holder.bind(getItem(position + 2)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ActorsRowStyleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<ActorsModel>() {
        override fun areItemsTheSame(
            oldItem: ActorsModel,
            newItem: ActorsModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: ActorsModel,
            newItem: ActorsModel
        ): Boolean {
            return oldItem == newItem
        }

    }

}