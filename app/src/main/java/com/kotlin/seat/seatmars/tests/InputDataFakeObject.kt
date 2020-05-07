package com.kotlin.seat.seatmars.tests

import com.kotlin.seat.seatmars.common.data.local.LocalAxis
import com.kotlin.seat.seatmars.common.data.local.LocalInputData
import com.kotlin.seat.seatmars.common.utils.NORTH

object InputDataFakeObject : InputDataFake() {

    fun getLocalInputData(): LocalInputData {
        return LocalInputData(
            topRightCorner = LocalAxis(5, 5),
            roverPosition = LocalAxis(1, 2),
            roverDirection = NORTH,
            movements = "LMLMLMLMM"
        )

    }
}