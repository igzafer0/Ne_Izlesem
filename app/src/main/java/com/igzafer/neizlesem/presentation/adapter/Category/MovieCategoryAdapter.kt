package com.igzafer.neizlesem.presentation.adapter.Category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.igzafer.neizlesem.data.model.category.CategoryModel
import com.igzafer.neizlesem.data.model.movie.MoviesModel

import com.igzafer.neizlesem.databinding.CategoryRowStyleBinding

class MovieCategoryAdapter :
    RecyclerView.Adapter<MovieCategoryAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: CategoryRowStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryModel) {
            binding.categoryTw.text = data.name
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(data)
                }
            }

        }
    }
    var onItemClickListener: ((CategoryModel) -> Unit)? = null
    fun setOnClickItemListener(listener: (CategoryModel) -> Unit) {
        onItemClickListener = listener
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]

        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CategoryRowStyleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    private val callback = object : DiffUtil.ItemCallback<CategoryModel>() {
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
    val differ = AsyncListDiffer(this, callback)
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}