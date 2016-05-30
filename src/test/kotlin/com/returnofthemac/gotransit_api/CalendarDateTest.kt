package com.returnofthemac.gotransit_api

import io.dropwizard.jackson.Jackson
import io.dropwizard.testing.FixtureHelpers.fixture
import org.junit.Test
import kotlin.test.assertEquals

class CalendarDateTest {
    val mapper = Jackson.newObjectMapper()
    @Test
    fun testCalendarDateSerialization() {
        val calendarDate = CalendarDate(
                serviceId = "5423-Fri",
                date = "20160624",
                exceptionType = 1)
        val expected = mapper.writeValueAsString(mapper.readValue(fixture("fixtures/calendarDate.json"), CalendarDate::class.java))
        val actual = mapper.writeValueAsString(calendarDate)
        assertEquals(expected, actual)
    }

    @Test
    fun testCalendarDateDeserialization() {
        val expected = CalendarDate(
                serviceId = "5423-Fri",
                date = "20160624",
                exceptionType = 1)
        val actual = mapper.readValue(fixture("fixtures/calendarDate.json"), CalendarDate::class.java)
        assertEquals(expected, actual)
    }
}
