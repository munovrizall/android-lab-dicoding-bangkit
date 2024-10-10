package com.artonov.dicodingevent.ui.finished

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artonov.dicodingevent.data.response.ListEventsItem
import com.artonov.dicodingevent.databinding.ItemFinishedEventBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FinishedAdapter : ListAdapter<ListEventsItem, FinishedAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ItemFinishedEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(eventsItem: ListEventsItem) {
            binding.apply {
                tvItemName.text = eventsItem.name
                tvEndTime.text = formatEndTime(eventsItem.endTime)
            }

            Glide.with(binding.root.context)
                .load(eventsItem.imageLogo)
                .into(binding.imgItemPhoto);
        }

        private fun formatEndTime(endTime: String?): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("EEEE, dd/MM/yyyy",  Locale("id", "ID"))
            val date: Date? = inputFormat.parse(endTime)

            return if (date != null) {
                outputFormat.format(date)
            } else {
                ""
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinishedAdapter.ViewHolder {
        val binding =
            ItemFinishedEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FinishedAdapter.ViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
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