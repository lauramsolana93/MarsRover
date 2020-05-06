package com.kotlin.seat.seatmars.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kotlin.seat.seatmars.common.data.InputData

class MarsRepository(
    private val local: MarsDataContract.Local
) : MarsDataContract.Repository {

    override val inputData: MutableLiveData<InputData> by lazy {
        MutableLiveData<InputData>()
    }

    override fun getDataFromJson(context: Context)  {
        inputData.value = local.getDataFromJson(context)
    }

}