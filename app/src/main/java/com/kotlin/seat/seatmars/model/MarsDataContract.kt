package com.kotlin.seat.seatmars.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kotlin.seat.seatmars.common.data.InputData

interface MarsDataContract {

    interface Repository {
        val inputData: MutableLiveData<InputData>
        fun getDataFromJson(context: Context)
    }

    interface Local {
        fun getDataFromJson(context: Context): InputData
    }
}