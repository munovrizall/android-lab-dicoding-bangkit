package com.artonov.dicodingevent.ui.finished

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.artonov.dicodingevent.data.response.ListEventsItem
import com.artonov.dicodingevent.databinding.FragmentFinishedBinding
import com.artonov.dicodingevent.ui.finished.FinishedViewModel

class FinishedFragment : Fragment() {

    private var _binding: FragmentFinishedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val finishedViewModel by viewModels<FinishedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishedBinding.inflate(inflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        finishedViewModel.listEvent.observe(viewLifecycleOwner) { upcomingEvents ->
            setFinishedEventData(upcomingEvents)
        }

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvListEvent.layoutManager = layoutManager

        finishedViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun setFinishedEventData(eventsItem: List<ListEventsItem>) {
        val adapter = FinishedAdapter()
        adapter.submitList(eventsItem)
        binding.rvListEvent.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}