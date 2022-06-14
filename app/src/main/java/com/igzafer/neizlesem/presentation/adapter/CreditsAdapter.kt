package com.igzafer.neizlesem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.igzafer.neizlesem.databinding.CreditsRowStyleBinding
import com.igzafer.neizlesem.domain.model.CreditsModel
import com.igzafer.neizlesem.util.ItemClickListener

class CreditsAdapter(private val onClickListener: ItemClickListener) :
    ListAdapter<CreditsModel, CreditsAdapter.CreditsViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreditsViewHolder {

        return CreditsViewHolder.create(parent)

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<CreditsModel>() {
        override fun areItemsTheSame(
            oldItem: CreditsModel,
            newItem: CreditsModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CreditsModel,
            newItem: CreditsModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: CreditsViewHolder, position: Int) {
        val creditsModel = getItem(position)
        holder.bind(creditsModel!!)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(creditsModel)
        }
    }

    class CreditsViewHolder(val binding: CreditsRowStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): CreditsViewHolder {
                val binding = CreditsRowStyleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CreditsViewHolder(binding)
            }
        }

        fun bind(creditsModel: CreditsModel) {
            binding.castModel = creditsModel
            binding.executePendingBindings()

        }
    }

}

