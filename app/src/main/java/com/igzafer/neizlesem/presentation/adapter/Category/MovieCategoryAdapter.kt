package com.igzafer.neizlesem.presentation.adapter.Category

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.igzafer.neizlesem.data.model.actor.ActorsModel
import com.igzafer.neizlesem.data.model.category.CategoryModel
import com.igzafer.neizlesem.data.util.onItemClickListenerCategory
import com.igzafer.neizlesem.data.util.onItemClickListenerMovie
import com.igzafer.neizlesem.databinding.ActorsRowStyleBinding
import com.igzafer.neizlesem.databinding.CategoryRowStyleBinding
import com.igzafer.neizlesem.presentation.adapter.Actors.PopularActorsRowAdapter

class MovieCategoryAdapter :
    PagingDataAdapter<CategoryModel, MovieCategoryAdapter.ViewHolder>(DiffUtilCallBack()) {


    inner class ViewHolder(val binding: CategoryRowStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryModel) {
            binding.categoryTw.text = data.name
            binding.root.setOnClickListener {
                onItemClickListenerCategory?.let {
                    it(data)
                }
            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CategoryRowStyleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<CategoryModel>() {
        override fun areItemsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean {
            return oldItem == newItem
        }

    }

}