package com.kotlin.seat.seatmars.common.data

data class MovementData(
    var coordiantes: Axis,
    var actualCoor: Axis,
    var dir: Char,
    var oldDir: Char,
    var hasToUpdateTable: Boolean,
    var rotation: Float,
    var isLastMove: Boolean
)