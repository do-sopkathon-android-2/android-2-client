package com.sopt.intime.ui.timeSetting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sopt.intime.ui.timeSetting.model.ButtonState
import com.sopt.intime.ui.timeSetting.model.InputMode
import com.sopt.intime.ui.timeSetting.model.ProgressState

class TimeSettingViewModel : ViewModel() {
    var inputMode: InputMode = InputMode.IDLE
    private val timeRecord: MutableLiveData<Pair<String, String>> = MutableLiveData()

    private val _progressState: MutableLiveData<ProgressState> =
        MutableLiveData(ProgressState.MORNING)
    val progressState: LiveData<ProgressState> get() = _progressState

    private val _startTime: MutableLiveData<String> = MutableLiveData(BLANK)
    val startTime: LiveData<String> get() = _startTime

    private val _endTime: MutableLiveData<String> = MutableLiveData(BLANK)
    val endTime: LiveData<String> get() = _endTime


    fun updateTime(dayUnit: String, hour: Int) {
        when (inputMode) {
            InputMode.START_TIME -> _startTime.value = hour.transformToHour(dayUnit)
            InputMode.END_TIME -> _endTime.value = hour.transformToHour(dayUnit)
            InputMode.IDLE -> throw IllegalStateException()
        }
    }

    fun updateProgressState(buttonState: ButtonState) {
        val presentState =
            _progressState.value?.ordinal ?: throw java.lang.IllegalArgumentException()

        when (buttonState) {
            ButtonState.NEXT -> _progressState.value = ProgressState.of(presentState + 1)
            ButtonState.PREVIOUS -> _progressState.value = ProgressState.of(presentState - 1)
        }
    }

    fun savePreviousTime() {
        timeRecord.value = (_startTime.value ?: BLANK) to (_endTime.value ?: BLANK)
    }

    fun restorePreviousTime() {
        _startTime.value = timeRecord.value?.first ?: BLANK
        _endTime.value = timeRecord.value?.second ?: BLANK
    }

    fun relocateTimeState() {
        _startTime.value = _endTime.value
        _endTime.value = BLANK
    }

    private fun Int.transformToHour(dayUnit: String): String {
        return when (dayUnit) {
            AM -> toString()
            PM -> (this + CORRECTION_VALUE).toString()
            else -> throw IllegalArgumentException()
        }
    }

    companion object {
        private const val CORRECTION_VALUE = 12
        private const val BLANK = ""
        private const val AM = "오전"
        private const val PM = "오후"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                TimeSettingViewModel()
            }
        }
    }
}