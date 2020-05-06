package com.kotlin.seat.seatmars.ui.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.seat.seatmars.R
import com.kotlin.seat.seatmars.common.data.Axis
import com.kotlin.seat.seatmars.common.data.InputData
import com.kotlin.seat.seatmars.common.utils.subscribe
import com.kotlin.seat.seatmars.di.injectModule
import com.kotlin.seat.seatmars.viewModel.MarsTableViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MarsPlateauActivity : AppCompatActivity() {

    private val vm: MarsTableViewModel by viewModel()


    lateinit var table: TableLayout
    lateinit var imageView: ImageView
    lateinit var row: TableRow
    var saveDir: Char? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectModule()
        initViews()
        setUpObservers()
    }

    private fun initViews() {
        table = findViewById(R.id.table_plateau)
        table.visibility = GONE
        table.scaleY = -1F
        table.scaleX = -1F
        start_button.setOnClickListener {
            table.removeAllViews()
            vm.count = 0
            vm.getDataFromJson(this)
        }
        move_button.setOnClickListener {
            var moves = vm.getNextMovements()
            if (moves != null) {
                if (moves.hasToUpdateTable) {
                    updateTable(moves.coordiantes)
                } else {
                    Toast.makeText(this, "POSITION GET IT, PRESS AGAIN TO MOVE", Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }

    }

    private fun setUpObservers() {
        vm.inputData.subscribe(this, this::drawTable)
    }


    private fun updateTable(coordinates: Axis) {
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
            //ERROR DATA NOT FOUND
        }

    }


    private fun drawTable(inputData: InputData) {
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


}
