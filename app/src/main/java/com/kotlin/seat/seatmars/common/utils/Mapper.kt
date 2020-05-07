package com.kotlin.seat.seatmars.common.utils

import com.kotlin.seat.seatmars.common.data.Axis
import com.kotlin.seat.seatmars.common.data.InputData
import com.kotlin.seat.seatmars.common.data.local.LocalAxis
import com.kotlin.seat.seatmars.common.data.local.LocalInputData

fun LocalInputData.mapToInputData(): InputData =
    InputData(
        topRightCorner = topRightCorner.mapToAxis(),
        roverPosition = roverPosition.mapToAxis(),
        roverDirection = roverDirection,
        movements = movements
    )

private fun LocalAxis.mapToAxis(): Axis =
    Axis(
        x = x,
        y = y
    )