package com.sopt.intime.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TodoListAllResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val `data`: Data,
)

@Serializable
data class Data(
    @SerialName("morning")
    val morning: List<DataContent>,
    @SerialName("lunch")
    val lunch: List<DataContent>,
    @SerialName("dinner")
    val dinner: List<DataContent>
)

@Serializable
data class DataContent(
    @SerialName("id")
    val id: Long,
    @SerialName("content")
    val content: String
)