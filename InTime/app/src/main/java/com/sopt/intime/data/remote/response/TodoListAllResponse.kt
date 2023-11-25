package com.sopt.intime.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TodoListAllResponse(
    @SerialName("data")
    val `data`: Data,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Int
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