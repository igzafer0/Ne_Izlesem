package com.igzafer.neizlesem.presentation.adapter.Actors

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.igzafer.neizlesem.BuildConfig
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.data.model.actor.CastModel
import com.igzafer.neizlesem.data.model.actor.actor_details.ActorsModel
import com.igzafer.neizlesem.databinding.ActorsRowStyleBinding
import com.igzafer.neizlesem.databinding.CastRowStyleBinding

class CastRowAdapter :
    RecyclerView.Adapter<CastRowAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: CastRowStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CastModel) {
            Log.d("winter", data.id.toString())
            if (data.profilePath == null) {
                binding.circleActorAvatar.setImageResource(R.mipmap.ic_launcher_round)
            } else {
                val posterPath = BuildConfig.PHOTO_URL + data.profilePath
                Glide.with(binding.circleActorAvatar.context).load(posterPath).thumbnail(
                    Glide.with(binding.circleActorAvatar.context).load(
                        R.drawable.loading
                    )
                )
                    .into(binding.circleActorAvatar)
                binding.actorName.text = data.name
                binding.characterName.text = data.character
            }

        }
    }

    private val callback = object : DiffUtil.ItemCallback<CastModel>() {
        override fun areItemsTheSame(
            oldItem: CastModel,
            newItem: CastModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: CastModel,
            newItem: CastModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CastRowStyleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}