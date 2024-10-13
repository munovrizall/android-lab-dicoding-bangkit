package com.artonov.dicodingevent.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.artonov.dicodingevent.R
import com.artonov.dicodingevent.data.response.Event
import com.artonov.dicodingevent.databinding.FragmentUpcomingDetailBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailFragment : Fragment() {
    private var _binding: FragmentUpcomingDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DetailFragmentArgs by navArgs()
        val eventId = args.eventId

        val factory = DetailViewModelFactory(eventId)
        val upcomingDetailViewModel =
            ViewModelProvider(this, factory)[DetailViewModel::class.java]

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
            tvEventLocation.text = event.cityName
            tvEventDate.text = formatTime(event.beginTime)
            if (remainingQuota == 0) {
                binding.tvEventQuota.text = context?.getString(R.string.quota_full)
            } else {
                val remainingQuotaText = context?.getString(R.string.remaining_quota, remainingQuota)
                binding.tvEventQuota.text = remainingQuotaText
            }
        }

        binding.tvEventDescription.text = HtmlCompat.fromHtml(
            event.description.toString(),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        Glide.with(this)
            .load(event.mediaCover)
            .into(binding.ivCover)

        binding.btnRegistration.setOnClickListener {
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
        val date: Date? = inputFormat.parse(time.toString())

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