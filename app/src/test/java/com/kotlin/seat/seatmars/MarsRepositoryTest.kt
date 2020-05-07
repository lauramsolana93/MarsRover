package com.kotlin.seat.seatmars

import android.content.Context
import com.kotlin.seat.seatmars.model.MarsDataContract
import com.kotlin.seat.seatmars.model.MarsRepository
import com.kotlin.seat.seatmars.tests.InputDataFakeObject
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MarsRepositoryTest {

    private var local = mockk<MarsDataContract.Local>()
    private lateinit var repository: MarsRepository
    private var localInputData = InputDataFakeObject.getLocalInputData()


    @Mock
    private lateinit var context: Context
  
    @Before
    fun init() {
        repository = MarsRepository(local)
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getInputDataFromJsonTest() {
        every { local.getDataFromJson(context) } returns Single.just(localInputData)
    }

}
