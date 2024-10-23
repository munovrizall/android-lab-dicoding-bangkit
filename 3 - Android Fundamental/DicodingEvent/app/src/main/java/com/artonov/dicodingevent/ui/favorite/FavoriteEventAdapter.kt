package com.artonov.dicodingevent.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artonov.dicodingevent.data.database.FavoriteEvent
import com.artonov.dicodingevent.data.response.ListEventsItem
import com.artonov.dicodingevent.databinding.ItemFinishedEventBinding
import com.bumptech.glide.Glide

class FavoriteEventAdapter : ListAdapter<FavoriteEvent, FavoriteEventAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    class ViewHolder(private val binding: ItemFinishedEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: FavoriteEvent) {
            binding.tvItemName.text = event.name
            binding.tvEndTime.text = event.beginTime
            Glide.with(binding.imgItemPhoto.context)
                .load(event.imageLogo)
                .into(binding.imgItemPhoto)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteEventAdapter.ViewHolder {
        val binding =
            ItemFinishedEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FavoriteEventAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteEvent>() {
            override fun areItemsTheSame(
                oldItem: FavoriteEvent,
                newItem: FavoriteEvent
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteEvent,
                newItem: FavoriteEvent
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}