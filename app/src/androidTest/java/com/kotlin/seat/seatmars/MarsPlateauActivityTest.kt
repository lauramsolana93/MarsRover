package com.kotlin.seat.seatmars

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.facebook.testing.screenshot.Screenshot
import com.facebook.testing.screenshot.ViewHelpers
import com.kotlin.seat.seatmars.tests.InputDataFakeObject
import com.kotlin.seat.seatmars.ui.activity.MarsPlateauActivity
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import com.schibsted.spain.barista.rule.BaristaRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MarsPlateauActivityTest {

    lateinit var mLayoutInflater: LayoutInflater
    lateinit var view: View

    val context: Context by lazy {
        InstrumentationRegistry.getInstrumentation().targetContext
    }


    @Rule
    @JvmField
    var mActivityTestRule : BaristaRule<MarsPlateauActivity> =
        BaristaRule.create(MarsPlateauActivity::class.java)


    @Rule
    @JvmField
    var reportHelper: ReportHelper = Factory.getReportHelper()

    @Before
    fun init() {
        mLayoutInflater = LayoutInflater.from(context)
        view = mLayoutInflater.inflate(R.layout.activity_main, null, false)
        ViewHelpers.setupView(view).setExactWidthDp(360).layout()

    }

    @Test
    fun checkViewOnEnter() {
        mActivityTestRule.launchActivity()
        reportHelper.label("MARS PLATEAU ACTIVITY ON ENTER")
        assertDisplayed(R.id.start_button)
        Screenshot.snap(view).record()
    }

    @Test
    fun checkTableDisplayed() {
        mActivityTestRule.launchActivity()
        reportHelper.label("MARS PLATEAU ACTIVITY ON CLICK START BUTTON")
        assertDisplayed(R.id.start_button)
        onView(withId(R.id.start_button)).perform(click())
        assertDisplayed(R.id.table_plateau)
        assertDisplayed(R.id.move_button)
        Screenshot.snap(view).record()
    }

    @Test
    fun checkTableUpdate() {
        mActivityTestRule.launchActivity()
        reportHelper.label("MARS PLATEAU ACTIVITY ON UPDATE TABLE")
        assertDisplayed(R.id.start_button)
        onView(withId(R.id.start_button)).perform(click())
        assertDisplayed(R.id.move_button)
        onView(withId(R.id.move_button)).perform(click())
        assertDisplayed(R.id.table_plateau)
        Screenshot.snap(view).record()
    }

    @Test
    fun checkFragmentDisplayed() {
        mActivityTestRule.launchActivity()
        reportHelper.label("MARS PLATEAU ACTIVITY ON LAST POSITION")
        assertDisplayed(R.id.start_button)
        onView(withId(R.id.start_button)).perform(click())
        var inputData = InputDataFakeObject.getLocalInputData()
        var movesList = inputData.movements.chunked(1)

        for (i in movesList.indices) {
            assertDisplayed(R.id.move_button)
            onView(withId(R.id.move_button)).perform(click())
            if (i == movesList.size - 1) {
                assertNotExist(R.id.move_button)
                mActivityTestRule.activityTestRule.activity.supportFragmentManager.findFragmentByTag("LAST MOVE")
                assertDisplayed(R.id.message)
            }
        }

        Screenshot.snap(view).record()
    }

}
