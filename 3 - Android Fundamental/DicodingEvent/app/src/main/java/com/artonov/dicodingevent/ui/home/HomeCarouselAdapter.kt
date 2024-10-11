package com.artonov.dicodingevent.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artonov.dicodingevent.data.response.ListEventsItem
import com.artonov.dicodingevent.databinding.ItemCarouselEventBinding
import com.bumptech.glide.Glide

class HomeCarouselAdapter : ListAdapter<ListEventsItem, HomeCarouselAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ItemCarouselEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(eventsItem: ListEventsItem) {
            Glide.with(binding.root.context)
                .load(eventsItem.imageLogo)
                .into(binding.imgItemPhoto);
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCarouselAdapter.ViewHolder {
        val binding =
            ItemCarouselEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeCarouselAdapter.ViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    override fun submitList(list: List<ListEventsItem>?) {
        if (list != null) {
            val limitedList = if (list.size > 5) list.take(5) else list
            super.submitList(limitedList)
        } else {
            super.submitList(null)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListEventsItem>() {
            override fun areItemsTheSame(
                oldItem: ListEventsItem,
                newItem: ListEventsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListEventsItem,
                newItem: ListEventsItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}