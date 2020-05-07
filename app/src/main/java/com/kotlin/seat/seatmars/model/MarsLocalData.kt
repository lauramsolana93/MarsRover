package com.kotlin.seat.seatmars.model

import android.content.Context
import com.google.gson.Gson
import com.kotlin.seat.seatmars.common.data.local.LocalInputData
import com.kotlin.seat.seatmars.common.utils.JsonParser
import io.reactivex.Single

class MarsLocalData : MarsDataContract.Local {

    private lateinit var jsonParser: JsonParser
    private val gson = Gson()
    lateinit var localInputData: Single<LocalInputData>

    override fun getDataFromJson(context: Context): Single<LocalInputData> {
        jsonParser = JsonParser()
        val jsonFileString = jsonParser.getJsonDataFromAssets(context, "input.json")

        return localInputData.map {
            gson.fromJson(jsonFileString, LocalInputData::class.java)
        }


    }

}