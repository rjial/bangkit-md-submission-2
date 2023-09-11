package com.rjial.githubprofile.ui

import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rjial.githubprofile.R
import com.rjial.githubprofile.util.EspressoIdlingResouce
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class MainActivityTest {
    private val testUsernameInput: String = "rjial"



    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResouce.idlingresource)

    }

    @Before
    fun resetTimeout() {
        IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS)
        IdlingPolicies.setIdlingResourceTimeout(26, TimeUnit.SECONDS)
    }

    @Test
    fun testSearchBar() {
        onView(withId(R.id.searchBar)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText(testUsernameInput), pressImeActionButton())
        onView(withId(R.id.pbSearch)).check(matches(withId(R.id.pbSearch)))

        onView(withId(R.id.rvProfiles)).check(matches(hasDescendant(withText(testUsernameInput))))
    }

}