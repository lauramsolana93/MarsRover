package com.kotlin.seat.seatmars.tests

import com.kotlin.seat.seatmars.common.utils.NORTH

open class InputDataFake {

    companion object {
        var topRightCorner: AxisFake = AxisFake(5, 5)
        var roverPosition: AxisFake = AxisFake(1, 2)
        var roverDirection: Char = NORTH
        var movements: String = "LMLMLMLMM"
    }
}





