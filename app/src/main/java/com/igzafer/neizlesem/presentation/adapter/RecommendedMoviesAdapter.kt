package com.igzafer.neizlesem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.igzafer.neizlesem.databinding.MoviesRowStyleBinding
import com.igzafer.neizlesem.databinding.MoviesVerticalRowStyleBinding
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.domain.model.MoviesModel
import com.igzafer.neizlesem.util.ItemClickListener

class RecommendedMoviesAdapter(private val onClickListener: ItemClickListener) :
    PagingDataAdapter<MoviesModel, RecyclerView.ViewHolder>(DiffUtilCallBack()) {

    class DiffUtilCallBack : DiffUtil.ItemCallback<MoviesModel>() {
        override fun areItemsTheSame(oldItem: MoviesModel, newItem: MoviesModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesModel, newItem: MoviesModel): Boolean {
            return oldItem == newItem
        }

    }

    class MovieViewHolder(val binding: MoviesRowStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val binding = MoviesRowStyleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return MovieViewHolder(binding)
            }
        }

        fun bind(movieModel: MoviesModel) {
            binding.moviesModel = movieModel
            binding.executePendingBindings()
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieModel = getItem(position)
        holder as MovieViewHolder
        holder.bind(movieModel!!)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movieModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder.from(parent)
    }
}