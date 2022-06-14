package com.igzafer.neizlesem.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.igzafer.neizlesem.databinding.SearchRowStyleBinding
import com.igzafer.neizlesem.domain.model.MultiSearchModel
import com.igzafer.neizlesem.util.ItemClickListener

class SearchAdapter(
    private val onClickListener: ItemClickListener,
) : PagingDataAdapter<MultiSearchModel, RecyclerView.ViewHolder>(DiffUtilCallBack()) {

    class DiffUtilCallBack : DiffUtil.ItemCallback<MultiSearchModel>() {
        override fun areItemsTheSame(
            oldItem: MultiSearchModel,
            newItem: MultiSearchModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MultiSearchModel,
            newItem: MultiSearchModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    class MovieViewHolder(val binding: SearchRowStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val binding = SearchRowStyleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return MovieViewHolder(binding)
            }
        }

        fun bind(multiSearchModel: MultiSearchModel) {
            binding.averageLayout.visibility =
                if (multiSearchModel.mediaType == "movie") View.VISIBLE else View.GONE
            binding.searchModel = multiSearchModel
            binding.executePendingBindings()
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val multiSearchModel = getItem(position)
        holder as MovieViewHolder


        holder.bind(multiSearchModel!!)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(multiSearchModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder.from(parent)
    }
}