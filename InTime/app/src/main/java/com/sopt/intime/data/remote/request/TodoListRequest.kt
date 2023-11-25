package com.sopt.intime.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoListRequest(
    @SerialName("content")
    val content: String,
    @SerialName("timeTag")
    val timeTag: String,
)
