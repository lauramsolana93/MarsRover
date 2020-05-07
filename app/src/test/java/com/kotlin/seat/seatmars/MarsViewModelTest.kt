package com.kotlin.seat.seatmars

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kotlin.seat.seatmars.common.data.InputData
import com.kotlin.seat.seatmars.model.MarsDataContract
import com.kotlin.seat.seatmars.viewModel.MarsTableViewModel
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MarsViewModelTest {

    private val repository: MarsDataContract.Repository = mock()
    private val liveData: Observer<InputData> = mock()
    private lateinit var viewModel: MarsTableViewModel

    @Mock
    private lateinit var context: Context

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = MarsTableViewModel(repository)
        whenever(repository.inputData).doReturn(MutableLiveData())
        viewModel.inputData.observeForever(liveData)
    }

    @Test
    fun testGetDataFromJson() {
        viewModel.getDataFromJson(context)
        verify(repository).getDataFromJson(context)
    }
    
}