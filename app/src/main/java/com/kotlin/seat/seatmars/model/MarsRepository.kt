package com.kotlin.seat.seatmars.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kotlin.seat.seatmars.common.data.InputData
import com.kotlin.seat.seatmars.common.utils.mapToInputData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MarsRepository(
    private val local: MarsDataContract.Local
) : MarsDataContract.Repository {

    override val inputData: MutableLiveData<InputData> by lazy {
        MutableLiveData<InputData>()
    }

    override fun getDataFromJson(context: Context) : Disposable {
        return local.getDataFromJson(context)
            .map { it.mapToInputData() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {data -> inputData.value = data},
                {error -> Log.e("DATA NOT FOUND", "DATA NOT FOUND $error")}
            )

    }

}