package com.sopt.intime.ui.home.adapter

import androidx.lifecycle.ViewModel
import com.sopt.intime.data.remote.response.DataContent

class HomeViewModel : ViewModel() {

    val mockData = listOf<DataContent>(
        DataContent(
            1,
            "Hello"
        ),
        DataContent(
            2,
            "Hello"
        ),
        DataContent(
            2,
            "Hello"
        ),
        DataContent(
            2,
            "Hello"
        ),
        DataContent(
            2,
            "Hello"
        ),
        DataContent(
            2,
            "Hello"
        ),
    )
}