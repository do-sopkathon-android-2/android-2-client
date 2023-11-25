package com.sopt.intime.ui.timeSetting.model

enum class ProgressState {
    MORNING, LUNCH, DINNER
    ;

    companion object {

        fun of(buttonState: Int): ProgressState = ProgressState.values()
            .find { emotion ->
                emotion.ordinal == buttonState
            } ?: MORNING
    }
}