package com.artonov.dicodingevent.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.artonov.dicodingevent.R
import com.artonov.dicodingevent.background.ReminderScheduler
import com.artonov.dicodingevent.data.preferences.DarkModePreferences
import com.artonov.dicodingevent.data.preferences.ReminderPreferences
import com.artonov.dicodingevent.data.preferences.dataStore
import com.artonov.dicodingevent.databinding.FragmentSettingBinding
import com.artonov.dicodingevent.databinding.FragmentUpcomingDetailBinding
import com.artonov.dicodingevent.ui.favorite.FavoriteViewModel
import com.artonov.dicodingevent.ui.favorite.FavoriteViewModelFactory
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private lateinit var reminderPreferences: ReminderPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = DarkModePreferences.getInstance(requireContext().dataStore)
        val settingViewModel = ViewModelProvider(this, SettingViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )
        settingViewModel.getThemeSettings().observe(viewLifecycleOwner) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                binding.switchDarkMode.isChecked = true
            } else {
                binding.switchDarkMode.isChecked = false
            }
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingViewModel.saveThemeSetting(isChecked)
        }

        reminderPreferences = ReminderPreferences(requireContext())
        val isDailyReminderEnabled = reminderPreferences.getDailyReminder()
        binding.switchDailyReminder.isChecked = isDailyReminderEnabled

        binding.switchDailyReminder.setOnCheckedChangeListener { _, isChecked ->
            reminderPreferences.setDailyReminder(isChecked)

            if (isChecked) {
                ReminderScheduler.scheduleDailyReminder(requireContext())
            } else {
                WorkManager.getInstance(requireContext()).cancelUniqueWork("DailyReminderWork")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}