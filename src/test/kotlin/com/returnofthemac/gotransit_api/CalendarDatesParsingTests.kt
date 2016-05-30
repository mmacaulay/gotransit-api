package com.returnofthemac.gotransit_api

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CalendarDatesParsingTests {
    val parser = GTFSParser()
    var calendarDates: List<CalendarDate> = emptyList()

    @Before
    fun before() {
        calendarDates = parser.parse<CalendarDate>("/gtfs-data/calendar_dates.txt")
    }

    @Test
    fun testParsesListOfCalendarDates() {
        assertEquals(168, calendarDates.size)
    }

    @Test
    fun testParsesACalendarDate() {
        val first = calendarDates[0]
        assertEquals("5423-Fri", first.serviceId)
        assertEquals("20160624", first.date)
        assertEquals(1, first.exceptionType)
    }
}
