package com.kotlin.seat.seatmars.common.data

data class MovementData(
    var coordiantes: Axis,
    var dir: Char,
    var oldDir: Char,
    var hasToUpdateTable: Boolean
)