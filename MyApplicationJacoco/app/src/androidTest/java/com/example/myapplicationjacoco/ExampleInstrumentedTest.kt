package com.example.myapplicationjacoco

import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAddition() {
        // Enter valid inputs
        onView(withId(R.id.input_a)).perform(typeText("15"), closeSoftKeyboard())
        onView(withId(R.id.input_b)).perform(typeText("10"), closeSoftKeyboard())

        // Click on "Add" button
        onView(withId(R.id.button_add)).perform(click())

        // Verify the result
        onView(withId(R.id.result_text)).check(matches(withText("Résultat : 25")))
    }

    @Test
    fun testSubtraction() {
        // Enter valid inputs
        onView(withId(R.id.input_a)).perform(typeText("20"), closeSoftKeyboard())
        onView(withId(R.id.input_b)).perform(typeText("5"), closeSoftKeyboard())

        // Click on "Subtract" button
        onView(withId(R.id.button_subtract)).perform(click())

        // Verify the result
        onView(withId(R.id.result_text)).check(matches(withText("Résultat : 15")))
    }

    @Test
    fun testMultiplication() {
        // Enter valid inputs
        onView(withId(R.id.input_a)).perform(typeText("4"), closeSoftKeyboard())
        onView(withId(R.id.input_b)).perform(typeText("5"), closeSoftKeyboard())

        // Click on "Multiply" button
        onView(withId(R.id.button_multiply)).perform(click())

        // Verify the result
        onView(withId(R.id.result_text)).check(matches(withText("Résultat : 20")))
    }

    @Test
    fun testDivision() {
        // Enter valid inputs
        onView(withId(R.id.input_a)).perform(typeText("10"), closeSoftKeyboard())
        onView(withId(R.id.input_b)).perform(typeText("2"), closeSoftKeyboard())

        // Click on "Divide" button
        onView(withId(R.id.button_divide)).perform(click())

        // Verify the result
        onView(withId(R.id.result_text)).check(matches(withText("Résultat : 5")))
    }

    @Test
    fun testDivisionByZero() {
        // Enter valid input in the first field
        onView(withId(R.id.input_a)).perform(typeText("10"), closeSoftKeyboard())

        // Enter zero in the second field
        onView(withId(R.id.input_b)).perform(typeText("0"), closeSoftKeyboard())

        // Click on "Divide" button
        onView(withId(R.id.button_divide)).perform(click())

        // Verify error message
        onView(withId(R.id.result_text)).check(matches(withText("Division par zéro n'est pas permise.")))
    }

    @Test
    fun testModulo() {
        // Enter valid inputs
        onView(withId(R.id.input_a)).perform(typeText("10"), closeSoftKeyboard())
        onView(withId(R.id.input_b)).perform(typeText("3"), closeSoftKeyboard())

        // Click on "Modulo" button
        onView(withId(R.id.button_modulo)).perform(click())

        // Verify the result
        onView(withId(R.id.result_text)).check(matches(withText("Résultat : 1")))
    }

    @Test
    fun testModuloByZero() {
        // Enter valid input in the first field
        onView(withId(R.id.input_a)).perform(typeText("10"), closeSoftKeyboard())

        // Enter zero in the second field
        onView(withId(R.id.input_b)).perform(typeText("0"), closeSoftKeyboard())

        // Click on "Modulo" button
        onView(withId(R.id.button_modulo)).perform(click())

        // Verify error message
        onView(withId(R.id.result_text)).check(matches(withText("Modulo par zéro n'est pas permis.")))
    }

    @Test
    fun testPower() {
        // Enter valid inputs
        onView(withId(R.id.input_a)).perform(typeText("2"), closeSoftKeyboard())
        onView(withId(R.id.input_b)).perform(typeText("3"), closeSoftKeyboard())

        // Click on "Power" button
        onView(withId(R.id.button_power)).perform(click())

        // Verify the result
        onView(withId(R.id.result_text)).check(matches(withText("Résultat : 8")))
    }

    @Test
    fun testEmptyInput() {
        // Leave both inputs empty
        onView(withId(R.id.input_a)).perform(clearText())
        onView(withId(R.id.input_b)).perform(clearText())

        // Click on "Add" button
        onView(withId(R.id.button_add)).perform(click())

        // Verify error message
        onView(withId(R.id.result_text)).check(matches(withText("Veuillez entrer des nombres valides.")))
    }

    @Test
    fun testInvalidInput() {
        // Enter invalid input in the first field
        onView(withId(R.id.input_a)).perform(typeText("abc"), closeSoftKeyboard())

        // Enter a valid number in the second field
        onView(withId(R.id.input_b)).perform(typeText("10"), closeSoftKeyboard())

        // Click on "Add" button
        onView(withId(R.id.button_add)).perform(click())

        // Verify error message
        onView(withId(R.id.result_text)).check(matches(withText("Veuillez entrer des nombres valides.")))
    }
}
