package com.kotlin.seat.seatmars.ui.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.seat.seatmars.R
import com.kotlin.seat.seatmars.common.data.Axis
import com.kotlin.seat.seatmars.common.data.InputData
import com.kotlin.seat.seatmars.common.data.MovementData
import com.kotlin.seat.seatmars.common.utils.*
import com.kotlin.seat.seatmars.di.injectModule
import com.kotlin.seat.seatmars.ui.fragment.BottomSheet
import com.kotlin.seat.seatmars.viewModel.MarsTableViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MarsPlateauActivity : AppCompatActivity() {

    private val vm: MarsTableViewModel by viewModel()

    private lateinit var table: TableLayout
    private lateinit var imageView: ImageView
    private lateinit var row: TableRow
    private lateinit var bottomSheet: BottomSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectModule()
        initViews()
        setListeners()
        setUpObservers()
    }

    private fun initViews() {
        table = findViewById(R.id.table_plateau)
        table.visibility = GONE
        table.scaleY = -1F
        table.scaleX = -1F
        bottomSheet = BottomSheet.newInstance()
    }

    private fun setListeners() {
        start_button.setOnClickListener {
            table.removeAllViews()
            vm.count = 0
            vm.getDataFromJson(this)
            vm.rotation = 0F
        }
        move_button.setOnClickListener {
            var moves = vm.getNextMovements()
            hasToUpdateTable(moves)

        }
        bottomSheet.isClosed = {
            if (it) {
                start_button.text = getString(R.string.start_button)
                table_plateau.removeAllViews()
            }

        }
    }


    private fun setUpObservers() {
        vm.inputData.subscribe(this, this::drawTable)
    }

    private fun drawTable(inputData: InputData) {
        if (inputData != null) {
            start_button.text = getString(R.string.restart_plateau)
            if (inputData.topRightCorner.x == 0 && inputData.topRightCorner.y == 0) {
                bottomSheet.show(
                    supportFragmentManager,
                    DATA_NOT_FOUND,
                    getString(R.string.data_not_found)
                )
            } else {
                for (i in 1..inputData.topRightCorner.y) {
                    row = TableRow(this)
                    row.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    for (j in 1..inputData.topRightCorner.x) {
                        imageView = ImageView(this)
                        if (i == inputData.roverPosition.y && j == inputData.roverPosition.x) {
                            imageView.apply {
                                layoutParams = TableRow.LayoutParams(
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT
                                )
                                background = getDrawable(R.drawable.rover)
                                scaleY = -1F
                            }
                        } else {
                            imageView.apply {
                                layoutParams = TableRow.LayoutParams(
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT
                                )
                                background = getDrawable(R.drawable.mars)
                            }
                        }

                        row.addView(imageView)
                    }
                    table.addView(row)
                }
                table.visibility = VISIBLE
                move_button.visibility = VISIBLE
            }

        } else {
            bottomSheet.show(
                supportFragmentManager,
                DATA_NOT_FOUND,
                getString(R.string.data_not_found)
            )
        }

    }

    private fun hasToUpdateTable(moves: MovementData?) {
        if (moves != null) {
            if (moves.hasToUpdateTable) {
                updateTable(moves.coordiantes, moves.rotation)
                if (moves.isLastMove) {
                    bottomSheet.show(
                        supportFragmentManager,
                        LAST_MOVE,
                        getString(R.string.reached_position) + getFinalPosition(
                            moves.coordiantes,
                            moves.dir,
                            moves.oldDir
                        )
                    )
                    move_button.visibility = GONE
                }

            } else {

                if (moves.isLastMove) {
                    move_button.visibility = GONE
                } else {
                    rotationTable(moves.actualCoor, moves.rotation)
                }
            }
        }
    }

    private fun rotationTable(coordinates: Axis, dirRotation: Float) {
        var inputData = vm.inputData.value
        table.removeAllViews()
        if (inputData != null) {
            for (i in 1..inputData.topRightCorner.y) {
                row = TableRow(this)
                row.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                for (j in 1..inputData.topRightCorner.x) {
                    imageView = ImageView(this)
                    if (i == coordinates.y && j == coordinates.x) {
                        imageView.apply {
                            layoutParams = TableRow.LayoutParams(
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT
                            )
                            background = getDrawable(R.drawable.rover)
                            scaleY = -1F
                            rotation += dirRotation
                        }
                    } else {
                        imageView.apply {
                            layoutParams = TableRow.LayoutParams(
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT
                            )
                            background = getDrawable(R.drawable.mars)
                        }
                    }


                    row.addView(imageView)
                }
                table.addView(row)
            }
            table.visibility = VISIBLE
        } else {
            bottomSheet.show(
                supportFragmentManager,
                DATA_NOT_FOUND,
                getString(R.string.data_not_found)
            )
        }
    }


    private fun updateTable(coordinates: Axis, dirRotation: Float) {
        var inputData = vm.inputData.value
        table.removeAllViews()
        if (inputData != null) {
            for (i in 1..inputData.topRightCorner.y) {
                row = TableRow(this)
                row.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                for (j in 1..inputData.topRightCorner.x) {
                    imageView = ImageView(this)
                    if (i == coordinates.y && j == coordinates.x) {
                        imageView.apply {
                            layoutParams = TableRow.LayoutParams(
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT
                            )
                            background = getDrawable(R.drawable.rover)
                            scaleY = -1F
                            rotation += dirRotation
                        }
                    } else {
                        imageView.apply {
                            layoutParams = TableRow.LayoutParams(
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT
                            )
                            background = getDrawable(R.drawable.mars)
                        }
                    }


                    row.addView(imageView)
                }
                table.addView(row)
            }
            table.visibility = VISIBLE
        } else {
            bottomSheet.show(
                supportFragmentManager,
                DATA_NOT_FOUND,
                getString(R.string.data_not_found)
            )
        }

    }

    private fun getFinalPosition(coordinates: Axis, dir: Char, oldDir: Char): String =
        if (dir == MOVE && oldDir != LEFT && oldDir != RIGHT) {
            " ${coordinates.x} ${coordinates.y} $NORTH "
        } else {
            " ${coordinates.x} ${coordinates.y} $dir "
        }


}
