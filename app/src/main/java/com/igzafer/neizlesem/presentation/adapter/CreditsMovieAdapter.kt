package com.igzafer.neizlesem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.igzafer.neizlesem.databinding.CreditsRowStyleBinding
import com.igzafer.neizlesem.databinding.MovieCreditsRowStyleBinding
import com.igzafer.neizlesem.domain.model.CreditsModel
import com.igzafer.neizlesem.domain.model.MovieCreditsModel
import com.igzafer.neizlesem.util.ItemClickListener

class CreditsMovieAdapter (private val onClickListener: ItemClickListener) :
    ListAdapter<MovieCreditsModel, CreditsMovieAdapter.CreditsMovieAdapter>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreditsMovieAdapter {

        return CreditsMovieAdapter.create(parent)

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<MovieCreditsModel>() {
        override fun areItemsTheSame(
            oldItem: MovieCreditsModel,
            newItem: MovieCreditsModel
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: MovieCreditsModel,
            newItem: MovieCreditsModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: CreditsMovieAdapter, position: Int) {
        val movieCreditsModel = getItem(position)
        holder.bind(movieCreditsModel!!)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movieCreditsModel)
        }
    }

    class CreditsMovieAdapter(val binding: MovieCreditsRowStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): CreditsMovieAdapter {
                val binding = MovieCreditsRowStyleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CreditsMovieAdapter(binding)
            }
        }

        fun bind(movieCreditsModel: MovieCreditsModel) {
            binding.moviesCreditsModel = movieCreditsModel
            binding.executePendingBindings()

        }
    }

}

