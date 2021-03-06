package com.kotlin.seat.seatmars

import android.content.Context
import com.kotlin.seat.seatmars.common.data.local.LocalInputData
import com.kotlin.seat.seatmars.common.utils.JsonParser
import com.kotlin.seat.seatmars.model.MarsLocalData
import com.kotlin.seat.seatmars.tests.InputDataFakeObject
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MarsLocalTest {

    private lateinit var jsonParser: JsonParser
    private lateinit var localInputData: LocalInputData
    private lateinit var marsLocalData: MarsLocalData

    @Mock
    private lateinit var context: Context

    @Before
    fun init() {
        marsLocalData = mockk<MarsLocalData>()
        localInputData = InputDataFakeObject.getLocalInputData()
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getInputDataFromJsonTest() {
        every { marsLocalData.getDataFromJson(context) } returns Single.just(localInputData)
    }


}