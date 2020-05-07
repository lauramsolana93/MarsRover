package com.kotlin.seat.seatmars.model

import android.content.Context
import com.google.gson.Gson
import com.kotlin.seat.seatmars.common.data.InputData
import com.kotlin.seat.seatmars.common.utils.JsonParser

class MarsLocalData : MarsDataContract.Local {

    lateinit var jsonParser: JsonParser
    val gson = Gson()
    lateinit var inputData: InputData

    override fun getDataFromJson(context: Context): InputData {
        jsonParser = JsonParser()
        val jsonFileString = jsonParser.getJsonDataFromAssets(context, "input.json")

        inputData = gson.fromJson(jsonFileString, InputData::class.java)
        return inputData
    }

}