package com.artonov.dicodingevent.ui.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artonov.dicodingevent.R
import com.artonov.dicodingevent.data.response.ListEventsItem
import com.artonov.dicodingevent.databinding.FragmentUpcomingBinding
import com.artonov.dicodingevent.databinding.ItemUpcomingEventBinding
import com.bumptech.glide.Glide

class UpcomingAdapter : ListAdapter<ListEventsItem, UpcomingAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ItemUpcomingEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(eventsItem: ListEventsItem) {
            binding.apply {
                tvItemName.text = eventsItem.name
                tvLocation.text = eventsItem.cityName
                tvQuota.text = "${eventsItem.registrants} / ${eventsItem.quota}"
            }

            Glide.with(binding.root.context)
                .load(eventsItem.imageLogo)   // Gambar saat terjadi error
                .into(binding.imgItemPhoto);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingAdapter.ViewHolder {
        val binding = ItemUpcomingEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingAdapter.ViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)

        holder.itemView.setOnClickListener() {
            val eventId = event.id.toString()
            val action = UpcomingFragmentDirections
                .actionNavigationUpcomingToUpcomingDetailFragment(eventId)

            holder.itemView.findNavController().navigate(action)
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