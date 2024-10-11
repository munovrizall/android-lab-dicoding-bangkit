package com.artonov.dicodingevent.ui.upcoming

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.artonov.dicodingevent.R
import com.artonov.dicodingevent.data.response.Event
import com.artonov.dicodingevent.data.response.ListEventsItem
import com.artonov.dicodingevent.databinding.FragmentUpcomingBinding
import com.artonov.dicodingevent.databinding.FragmentUpcomingDetailBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UpcomingDetailFragment : Fragment() {
    private var _binding: FragmentUpcomingDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpcomingDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: UpcomingDetailFragmentArgs by navArgs()
        val eventId = args.eventId

        val factory = UpcomingDetailViewModelFactory(eventId)
        val upcomingDetailViewModel =
            ViewModelProvider(this, factory).get(UpcomingDetailViewModel::class.java)

        upcomingDetailViewModel.event.observe(viewLifecycleOwner) { eventData ->
            setEventData(eventData)
        }

        upcomingDetailViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun setEventData(event: Event) {
        val remainingQuota = event.quota?.minus(event.registrants!!)

        binding.apply {
            tvEventName.text = event.name
            tvEventAdmin.text = event.ownerName
            tvEventDescription.text = event.description
            tvEventLocation.text = event.cityName
            tvEventDate.text = formatTime(event.beginTime)
            if (remainingQuota == 0) {
                tvEventQuota.text = "Penuh"
            } else {
                tvEventQuota.text = "$remainingQuota orang lagi"
            }
        }

        Glide.with(this)
            .load(event.mediaCover)
            .into(binding.ivCover)

        binding.btnRegistration.setOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(event.link)
            startActivity(intent)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun formatTime(time: String?): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE, dd/MM/yyyy", Locale("id", "ID"))
        val date: Date? = inputFormat.parse(time)

        return if (date != null) {
            outputFormat.format(date)
        } else {
            ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}