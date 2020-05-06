package com.kotlin.seat.seatmars.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.seat.seatmars.common.data.Axis
import com.kotlin.seat.seatmars.common.data.InputData
import com.kotlin.seat.seatmars.common.data.MovementData
import com.kotlin.seat.seatmars.common.utils.*
import com.kotlin.seat.seatmars.model.MarsDataContract

class MarsTableViewModel constructor
    (private val repository: MarsDataContract.Repository) :
    ViewModel() {

    var count = 0
    var axisPossibleMoves = AxisPossibleMoves(0, 0)
    var directionAfterRotation = 'N'
    var rotation = 0F
    lateinit var coordinates: Axis
    lateinit var actualCoor: Axis
    var saveDir: Char? = null

    val inputData: MutableLiveData<InputData> by lazy {
        repository.inputData
    }

    fun getDataFromJson(context: Context) {
        repository.getDataFromJson(context)
    }

    fun getNextMovements(): MovementData? {
        var movementData: MovementData? = null
        var dir: Char = MOVE
        var oldDir: Char = MOVE
        var inputData: InputData? = repository.inputData.value

        if (inputData != null) {
            var movesList = inputData.movements.chunked(1)

            when {
                count < movesList.size -> {
                    dir = movesList[count].single()
                    oldDir = movesList[count].single()
                    if (count == 0) {
                        directionAfterRotation = inputData.roverDirection
                        axisPossibleMoves =
                            AxisPossibleMoves(inputData.roverPosition.x, inputData.roverPosition.y)
                        coordinates = axisPossibleMoves
                        actualCoor = axisPossibleMoves
                        count++
                        movementData = doMovements(dir, oldDir)
                    } else {
                        oldDir = movesList[count - 1].single()
                        axisPossibleMoves =
                            AxisPossibleMoves(coordinates.x, coordinates.y)
                        actualCoor = axisPossibleMoves

                        movementData = doMovements(dir, oldDir)
                        if (count == movesList.size - 1) {
                            movementData.hasToUpdateTable = true
                            movementData.isLastMove = true
                        }
                        count++

                    }
                    movementData.actualCoor = actualCoor
                    return movementData


                }
                else -> {
                    /*movementData = doMovements(dir, oldDir)
                        movementData.hasToUpdateTable = false
                        movementData.isLastMove = true*/
                    return null
                }
            }
        }
        return null

    }

    private fun doMovements(dir: Char, oldDir: Char): MovementData {
        var hasToUpdateTable = false
        when (dir) {
            LEFT -> {
                saveDir = dir
                directionAfterRotation =
                    axisPossibleMoves.rotationLeft(directionAfterRotation)
                coordinates = axisPossibleMoves.nextPossibleMove(directionAfterRotation)
                rotation -= 90F
            }
            RIGHT -> {
                saveDir = dir
                directionAfterRotation =
                    axisPossibleMoves.rotationRight(directionAfterRotation)
                coordinates =
                    axisPossibleMoves.nextPossibleMove(directionAfterRotation)
                rotation += 90F
            }
            MOVE -> {
                saveDir = dir
                rotation = rotation
                if (oldDir != LEFT && oldDir != RIGHT) {
                    coordinates = axisPossibleMoves.nextPossibleMove(NORTH)
                }
                if (coordinates.x in 1..5 && coordinates.y in 1..5) {
                    hasToUpdateTable = true
                }
            }
        }
        return MovementData(coordinates, actualCoor, dir, oldDir, hasToUpdateTable, rotation, false)
    }

}