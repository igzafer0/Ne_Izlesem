package com.igzafer.neizlesem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.igzafer.neizlesem.data.model.MoviesModel
import com.igzafer.neizlesem.databinding.PopularMoviesRowBinding

class PopularMoviesAdapter :
    PagingDataAdapter<MoviesModel, PopularMoviesAdapter.ViewHolder>(DiffUtilCallBack()) {


    inner class ViewHolder(val binding: PopularMoviesRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesModel) {
            binding.twTitle.text = data.title
            val posterPath = "https://image.tmdb.org/t/p/w500" + data.posterPath
            Glide.with(binding.imPoster.context).load(posterPath)
                .into(binding.imPoster)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PopularMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<MoviesModel>() {
        override fun areItemsTheSame(
            oldItem: MoviesModel,
            newItem: MoviesModel
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: MoviesModel,
            newItem: MoviesModel
        ): Boolean {
            return oldItem == newItem
        }

    }
}