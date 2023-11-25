package com.sopt.intime.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserTimeDataResponse(
    @SerialName("morningStart")
    val morningStart: Int,
    @SerialName("morningEnd")
    val morningEnd: Int,
    @SerialName("lunchStart")
    val lunchStart: Int,
    @SerialName("lunchEnd")
    val lunchEnd: Int,
    @SerialName("dinnerStart")
    val dinnerStart: Int,
    @SerialName("dinnerEnd")
    val dinnerEnd: Int,
    @SerialName("nightStart")
    val nightStart: Int,
    @SerialName("nightEnd")
    val nightEnd: Int
)