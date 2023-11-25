package com.sopt.intime.ui.timeSetting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sopt.intime.data.api.InTimeService
import com.sopt.intime.data.api.RetrofitServicePool
import com.sopt.intime.data.remote.request.UserTimeRequest
import com.sopt.intime.ui.timeSetting.model.ButtonState
import com.sopt.intime.ui.timeSetting.model.InputMode
import com.sopt.intime.ui.timeSetting.model.ProgressState
import kotlinx.coroutines.launch

class TimeSettingViewModel(
    private val inTimeService: InTimeService
) : ViewModel() {
    var inputMode: InputMode = InputMode.IDLE
    private val timeRecord: MutableLiveData<MutableList<Pair<String, String>>> = MutableLiveData(
        mutableListOf()
    )

    private val _progressState: MutableLiveData<ProgressState> =
        MutableLiveData(ProgressState.MORNING)
    val progressState: LiveData<ProgressState> get() = _progressState

    private val _startTime: MutableLiveData<String> = MutableLiveData(BLANK)
    val startTime: LiveData<String> get() = _startTime

    private val _endTime: MutableLiveData<String> = MutableLiveData(BLANK)
    val endTime: LiveData<String> get() = _endTime

    fun saveTimeRecord() {
        viewModelScope.launch {
            runCatching {
                inTimeService.postUserTime(
                    userId = USER_ID,
                    userTimeRequest = UserTimeRequest(
                        morningStart = timeRecord.value?.first()?.first?.formatTime() ?: "00:00",
                        lunchStart = timeRecord.value?.first()?.second?.formatTime() ?: "00:00",
                        dinnerStart = startTime.value?.formatTime() ?: "00:00",
                        dinnerEnd = endTime.value?.formatTime() ?: "00:00"
                    )
                )
            }.onSuccess {
                Log.d("status", it.status.toString())
            }.onFailure {
                Log.d("status", it.toString())
            }
        }
    }

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
        Log.d("123123", _startTime.value.toString())
        Log.d("1231234", _endTime.value.toString())
        timeRecord.value?.add((_startTime.value ?: BLANK) to (_endTime.value ?: BLANK))

        Log.d("123123123", timeRecord.value.toString())
        relocateTimeState()
    }

    fun restorePreviousTime() {
        _startTime.value = timeRecord.value?.last()?.first ?: BLANK
        _endTime.value = timeRecord.value?.last()?.second ?: BLANK
    }

    private fun relocateTimeState() {
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

    private fun String.formatTime(): String {
        val hour = this.toInt()
        return if (hour < 10) {
            "0${hour}:00"
        } else {
            "${hour}:00"
        }
    }

    companion object {
        private const val CORRECTION_VALUE = 12
        private const val USER_ID = 1L
        private const val BLANK = ""
        private const val AM = "오전"
        private const val PM = "오후"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                TimeSettingViewModel(
                    RetrofitServicePool.todoService
                )
            }
        }
    }
}