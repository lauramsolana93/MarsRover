package com.kotlin.seat.seatmars.tests

import com.kotlin.seat.seatmars.common.data.Axis
import com.kotlin.seat.seatmars.common.data.InputData
import com.kotlin.seat.seatmars.common.utils.NORTH

object InputDataFakeObject : InputDataFake() {

    fun getInputData(): InputData {
        return InputData(
            topRightCorner = Axis(5, 5),
            roverPosition = Axis(1, 2),
            roverDirection = NORTH,
            movements = "LMLMLMLMM"
        )

    }
}