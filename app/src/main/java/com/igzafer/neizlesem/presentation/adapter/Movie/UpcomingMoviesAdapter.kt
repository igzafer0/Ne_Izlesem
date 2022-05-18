package com.igzafer.neizlesem.presentation.adapter.Movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.data.model.movie.MoviesModel

import com.igzafer.neizlesem.databinding.MoviesRowStyleBinding

class UpcomingMoviesAdapter:PagingDataAdapter<MoviesModel, UpcomingMoviesAdapter.ViewHolder>(
    DiffUtilCallBack()
) {


    inner class ViewHolder(val binding: MoviesRowStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesModel) {
            if(data.posterPath!=null){
                val posterPath = "https://image.tmdb.org/t/p/w500" + data.posterPath
                Glide.with(binding.imPoster.context).load(posterPath)
                    .into(binding.imPoster)
            }else{
                binding.imPoster.setImageResource(R.mipmap.ic_launcher_round)
            }

            binding.cardView.setOnClickListener {
                onItemClickListener?.let {
                    it(data)
                }
            }
        }
    }
    var onItemClickListener: ((MoviesModel) -> Unit)? = null
    fun setOnClickItemListener(listener: (MoviesModel) -> Unit) {
        onItemClickListener = listener
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MoviesRowStyleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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