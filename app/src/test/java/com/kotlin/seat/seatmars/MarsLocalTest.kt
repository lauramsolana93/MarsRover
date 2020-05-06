package com.kotlin.seat.seatmars

import android.content.Context
import android.content.res.Resources
import com.kotlin.seat.seatmars.common.data.InputData
import com.kotlin.seat.seatmars.model.MarsLocalData
import com.kotlin.seat.seatmars.tests.InputDataFakeObject
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MarsLocalTest {

    private lateinit var inputData: InputData
    private lateinit var marsLocalData: MarsLocalData
    private var respose = "{\n" +
            "  \"topRightCorner\": {\n" +
            "    \"x\": 5,\n" +
            "    \"y\": 5\n" +
            "  },\n" +
            "  \"roverPosition\": {\n" +
            "    \"x\": 1,\n" +
            "    \"y\": 2\n" +
            "  },\n" +
            "  \"roverDirection\": \"N\",\n" +
            "  \"movements\": \"LMLMLMLMM\"\n" +
            "}"


    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var resources: Resources

    @Before
    fun init() {
        marsLocalData = MarsLocalData()
        inputData = InputDataFakeObject.getInputData()
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getInputDataFromJsonTest() {
        `when`(marsLocalData.getDataFromJson(context)).thenReturn(inputData)
        //`when`(marsLocalData.gson.fromJson(respose,InputData::class.java)).thenReturn(inputData)
    }




}