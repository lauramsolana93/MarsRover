package com.kotlin.seat.seatmars.common.utils

import com.kotlin.seat.seatmars.common.data.Axis

class AxisPossibleMoves(x: Int, y: Int) : Axis(x, y) {

    private fun goNorth(): AxisPossibleMoves = AxisPossibleMoves(x, y + 1)
    private fun goSouth(): AxisPossibleMoves = AxisPossibleMoves(x, y - 1)
    private fun goWest(): AxisPossibleMoves = AxisPossibleMoves(x + 1, y)
    private fun goEast(): AxisPossibleMoves = AxisPossibleMoves(x - 1, y)

    fun nextPossibleMove(direction: Char): AxisPossibleMoves =
        when (direction) {
            NORTH -> goNorth()
            SOUTH -> goSouth()
            EAST -> goEast()
            WEST -> goWest()
            else -> goNorth()
        }

    fun rotationLeft(direction: Char): Char = when(direction){
        NORTH -> WEST
        WEST -> SOUTH
        SOUTH -> EAST
        EAST -> NORTH
        else -> NORTH
    }

    fun rotationRight(direction: Char): Char = when(direction){
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
        else -> NORTH
    }


}

