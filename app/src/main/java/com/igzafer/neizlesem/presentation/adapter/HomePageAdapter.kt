package com.igzafer.neizlesem.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.igzafer.neizlesem.BuildConfig
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.databinding.MoviesRowStyleBinding
import com.igzafer.neizlesem.databinding.MoviesVerticalRowStyleBinding
import com.igzafer.neizlesem.databinding.RecyTextRowStyleBinding
import com.igzafer.neizlesem.domain.model.HomeModel
import com.igzafer.neizlesem.domain.model.MoviesModel
import com.igzafer.neizlesem.util.ItemClickListener


class HomePageAdapter(
    private val onClickListener: ItemClickListener,
) : ListAdapter<HomeModel, RecyclerView.ViewHolder>(DiffUtilCallBack()) {
    companion object {
        const val MOVIE_VIEW = 0
        const val TEXT_VIEW = 1
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<HomeModel>() {
        override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
            return oldItem.moviesModel!!.id == newItem.moviesModel!!.id
        }

        override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
            return oldItem == newItem
        }

    }

    class MovieViewHolder(val binding: MoviesVerticalRowStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val binding = MoviesVerticalRowStyleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return MovieViewHolder(binding)
            }
        }

        fun bind(movieModel: HomeModel) {

            binding.data = movieModel
            binding.executePendingBindings()
        }

    }

    class TextViewHolder(val binding: RecyTextRowStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val binding = RecyTextRowStyleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return TextViewHolder(binding)
            }
        }

        fun bind(value: HomeModel) {
            binding.data = value
            binding.executePendingBindings()
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieModel = getItem(position)
        if (movieModel!!.viewType == TEXT_VIEW) {
            (holder as TextViewHolder).bind(movieModel)

        } else {
            (holder as MovieViewHolder).bind(movieModel)
            holder.itemView.setOnClickListener {
                onClickListener.onClick(movieModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val movieModel = getItem(position)
        return if (movieModel.viewType == TEXT_VIEW) {
            TEXT_VIEW
        } else {
            MOVIE_VIEW

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MOVIE_VIEW) {
            MovieViewHolder.from(parent)
        } else {
            TextViewHolder.from(parent)

        }

    }
}