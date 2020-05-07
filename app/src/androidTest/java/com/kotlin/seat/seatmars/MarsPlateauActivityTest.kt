package com.kotlin.seat.seatmars

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.kotlin.seat.seatmars.tests.InputDataFakeObject
import com.kotlin.seat.seatmars.ui.activity.MarsPlateauActivity
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MarsPlateauActivityTest {


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MarsPlateauActivity::class.java)


    @Rule
    @JvmField
    var reportHelper: ReportHelper = Factory.getReportHelper()

    @Before
    fun init() {
        mActivityTestRule.activity

    }

    @Test
    fun checkViewOnEnter() {
        reportHelper.label("MARS PLATEAU ACTIVITY ON ENTER")
        onView(withId(R.id.start_button)).check(matches(isDisplayed()))
    }

    @Test
    fun checkTableDisplayed() {
        reportHelper.label("MARS PLATEAU ACTIVITY ON CLICK START BUTTON")
        onView(withId(R.id.start_button)).perform(click())
        onView(withId(R.id.table_plateau)).check(matches(isDisplayed()))
        onView(withId(R.id.move_button)).check(matches(isDisplayed()))
    }

    @Test
    fun checkTableUpdate() {
        reportHelper.label("MARS PLATEAU ACTIVITY ON UPDATE TABLE")
        onView(withId(R.id.start_button)).check(matches(isDisplayed()))
        onView(withId(R.id.start_button)).perform(click())
        onView(withId(R.id.move_button)).check(matches(isDisplayed()))
        onView(withId(R.id.move_button)).perform(click())
        onView(withId(R.id.table_plateau)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentDisplayed() {
        reportHelper.label("MARS PLATEAU ACTIVITY ON LAST POSITION")
        onView(withId(R.id.start_button)).check(matches(isDisplayed()))
        onView(withId(R.id.start_button)).perform(click())
        var inputData = InputDataFakeObject.getLocalInputData()
        var movesList = inputData.movements.chunked(1)

        for (i in movesList.indices) {
            onView(withId(R.id.move_button)).check(matches(isDisplayed()))
            onView(withId(R.id.move_button)).perform(click())
            if (i == movesList.size - 1) {
                onView(withId(R.id.move_button)).check(doesNotExist())
                mActivityTestRule.activity.supportFragmentManager.findFragmentByTag("LAST MOVE")
                onView(withId(R.id.message)).check(matches(isDisplayed()))
            }
        }
    }


}
