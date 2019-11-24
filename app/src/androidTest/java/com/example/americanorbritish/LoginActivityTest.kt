package com.example.americanorbritish


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Rule
    @JvmField
    val noAnimationTestRule = NoAnimationTestRule()

    @Test
    fun logintothemainscreen() {
        onView(withId(R.id.email)).check(matches(isDisplayed()))
        onView(withId(R.id.password)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_reset_password)).check(matches(isDisplayed()))

        val eMail = "testwatttest@gmail.com"
        val pAssword = "0206Wat@ru."

        onView(allOf(withId(R.id.email), isDisplayed())).perform(
            replaceText(eMail),
            closeSoftKeyboard()
        )
        onView(allOf(withId(R.id.password), isDisplayed())).perform(
            typeText(pAssword),
            closeSoftKeyboard()
        )
        onView(allOf(withId(R.id.btn_login), isDisplayed())).perform(click())
    }

}
