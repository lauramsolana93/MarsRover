package com.kotlin.seat.seatmars.common.data.local

import com.google.gson.annotations.SerializedName

class LocalInputData(
    @SerializedName("topRightCorner")
    val topRightCorner: LocalAxis,
    @SerializedName("roverPosition")
    val roverPosition: LocalAxis,
    @SerializedName("roverDirection")
    val roverDirection: Char,
    @SerializedName("movements")
    val movements: String

)