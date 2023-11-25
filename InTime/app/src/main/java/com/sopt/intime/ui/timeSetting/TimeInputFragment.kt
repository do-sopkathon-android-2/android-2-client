package com.sopt.intime.ui.timeSetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sopt.intime.R
import com.sopt.intime.databinding.FragmentTimePickerBinding

class TimeInputFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentTimePickerBinding? = null
    private val binding: FragmentTimePickerBinding
        get() = _binding ?: error("binding이 초기화 되지 않았습니다.")
    private val timeSettingViewModel: TimeSettingViewModel by activityViewModels {
        TimeSettingViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTimePickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPicker()
        setEventOnClick()
    }

    private fun setupPicker() {
        with(binding) {
            npTimePickerDay.maxValue = 1
            npTimePickerDay.displayedValues = DAY_UNIT

            npTimePickerHour.minValue = 1
            npTimePickerHour.maxValue = 12
            npTimePickerHour.setFormatter {
                String.format(getString(R.string.np_time_setting_format), it)
            }
        }
    }

    private fun setEventOnClick() {
        var dayUnit = AM
        var hour = 1

        binding.npTimePickerDay.setOnValueChangedListener { numberPicker, _, _ ->
            dayUnit = if (numberPicker.value == 0) AM else PM
        }

        binding.npTimePickerHour.setOnValueChangedListener { numberPicker, _, _ ->
            hour = numberPicker.value
        }

        binding.btnTimeSettingSave.setOnClickListener {
            timeSettingViewModel.updateTime(dayUnit, hour)
            dismiss()
        }
    }

    companion object {
        private const val AM = "오전"
        private const val PM = "오후"
        private val DAY_UNIT = arrayOf(AM, PM)
    }
}
