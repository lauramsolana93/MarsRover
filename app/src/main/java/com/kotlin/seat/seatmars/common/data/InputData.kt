package com.kotlin.seat.seatmars.common.data

import com.google.gson.annotations.SerializedName

class InputData(
    @SerializedName("topRightCorner")
    val topRightCorner: Axis,
    @SerializedName("roverPosition")
    val roverPosition: Axis,
    @SerializedName("roverDirection")
    val roverDirection: Char,
    @SerializedName("movements")
    val movements: String

)