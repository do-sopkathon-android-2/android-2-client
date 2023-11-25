package com.sopt.intime.ui.timeSetting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.intime.databinding.ActivityTimeSettingBinding
import com.sopt.intime.ui.home.HomeActivity
import com.sopt.intime.ui.timeSetting.model.ButtonState
import com.sopt.intime.ui.timeSetting.model.InputMode
import com.sopt.intime.ui.timeSetting.model.ProgressState

class TimeSettingActivity : AppCompatActivity() {
    private val binding: ActivityTimeSettingBinding by lazy {
        ActivityTimeSettingBinding.inflate(layoutInflater)
    }
    private val timeSettingViewModel: TimeSettingViewModel by viewModels {
        TimeSettingViewModel.Factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindingViewModel()
        setEventOnClick()
        observeProgressState()
    }

    private fun setEventOnClick() {
        binding.tvTimeSettingTimeInputStart.setOnClickListener {
            timeSettingViewModel.inputMode = InputMode.START_TIME
            val timeInputFragment = TimeInputFragment()
            timeInputFragment.show(supportFragmentManager, timeInputFragment.tag)
        }

        binding.tvTimeSettingTimeInputEnd.setOnClickListener {
            timeSettingViewModel.inputMode = InputMode.END_TIME
            val timeInputFragment = TimeInputFragment()
            timeInputFragment.show(supportFragmentManager, timeInputFragment.tag)
        }

        binding.btnTimeSettingNext.setOnClickListener {
            val hasBlank =
                timeSettingViewModel.startTime.value.isNullOrBlank() or timeSettingViewModel.endTime.value.isNullOrBlank()

            if ((timeSettingViewModel.progressState.value == ProgressState.DINNER) and !hasBlank) navigateToHomeActivity()
            // 데이터바인딩으로 빼야함

            if (hasBlank) return@setOnClickListener

            timeSettingViewModel.savePreviousTime()
            timeSettingViewModel.updateProgressState(ButtonState.NEXT)
        }

        binding.ivTimeSettingBackButton.setOnClickListener {
            if (timeSettingViewModel.progressState.value == ProgressState.MORNING) finish()
            timeSettingViewModel.restorePreviousTime()
            timeSettingViewModel.updateProgressState(ButtonState.PREVIOUS)
        }
    }

    private fun bindingViewModel() {
        binding.lifecycleOwner = this
        binding.vm = timeSettingViewModel
    }

    private fun observeProgressState() {
        timeSettingViewModel.progressState.observe(this) { progressState ->
            when (progressState) {
                ProgressState.MORNING -> setupMorningView()
                ProgressState.LUNCH -> setupLunchView()
                ProgressState.DINNER -> setupDinnerView()
            }
        }
    }


    private fun navigateToHomeActivity() {
        // 서버통신
        // 런치싱글탑
        // 온보딩 여부 저장해야함
        startActivity(HomeActivity.from(this))
    }

    private fun setupMorningView() {
        binding.lpiTimeSetting.progress = 33
        binding.tvTimeSettingTitleQuestion.text = "Q. 당신의 아침은 언제인가요?"
    }

    private fun setupLunchView() {
        binding.lpiTimeSetting.progress = 67
        binding.tvTimeSettingTitleQuestion.text = "Q. 당신의 점심은 언제인가요?"
        timeSettingViewModel.relocateTimeState()
    }

    private fun setupDinnerView() {
        binding.lpiTimeSetting.progress = 100
        binding.tvTimeSettingTitleQuestion.text = "Q. 당신의 저녁은 언제인가요?"
        timeSettingViewModel.relocateTimeState()
    }


    companion object {
        fun from(context: Context): Intent = Intent(context, TimeSettingActivity::class.java)
    }
}
