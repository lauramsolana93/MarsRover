package com.kotlin.seat.seatmars.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kotlin.seat.seatmars.common.data.InputData
import com.kotlin.seat.seatmars.common.data.local.LocalInputData
import io.reactivex.Single
import io.reactivex.disposables.Disposable

interface MarsDataContract {

    interface Repository {
        val inputData: MutableLiveData<InputData>
        fun getDataFromJson(context: Context): Disposable
    }

    interface Local {
        fun getDataFromJson(context: Context): Single<LocalInputData>
    }
}