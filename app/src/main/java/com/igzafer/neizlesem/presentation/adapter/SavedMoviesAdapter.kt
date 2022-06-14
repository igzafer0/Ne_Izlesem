package com.igzafer.neizlesem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.databinding.MoviesVerticalRowStyleBinding
import com.igzafer.neizlesem.domain.model.HomeModel
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.domain.model.MoviesModel
import com.igzafer.neizlesem.util.ItemClickListener


class SavedMoviesAdapter(private val onClickListener: ItemClickListener) :
    ListAdapter<MovieDetailsModel, SavedMoviesAdapter.SavedMoviesViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SavedMoviesViewHolder {

        return SavedMoviesViewHolder.create(parent)

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<MovieDetailsModel>() {
        override fun areItemsTheSame(
            oldItem: MovieDetailsModel,
            newItem: MovieDetailsModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieDetailsModel,
            newItem: MovieDetailsModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: SavedMoviesViewHolder, position: Int) {
        val movieModel = getItem(position)
        holder.bind(movieModel!!)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movieModel)
        }
    }

    class SavedMoviesViewHolder(val binding: MoviesVerticalRowStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): SavedMoviesViewHolder {
                val binding = MoviesVerticalRowStyleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return SavedMoviesViewHolder(binding)
            }
        }

        fun bind(movieDetailsModel: MovieDetailsModel) {
            binding.data = HomeModel(
                0, null, MoviesModel(
                    movieDetailsModel.id,
                    movieDetailsModel.posterPath,
                    movieDetailsModel.title,
                    movieDetailsModel.releaseDate,
                    movieDetailsModel.voteAverage
                )
            )
            binding.executePendingBindings()

        }
    }

}

