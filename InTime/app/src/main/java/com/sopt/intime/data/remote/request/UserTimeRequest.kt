package com.sopt.intime.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserTimeRequest(
    @SerialName("morningStart")
    val morningStart: String,
    @SerialName("lunchStart")
    val lunchStart: String,
    @SerialName("dinnerStart")
    val dinnerStart: String,
    @SerialName("dinnerEnd")
    val dinnerEnd: String,
)
