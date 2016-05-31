package com.returnofthemac.gotransit_api.resources

import org.junit.Test
import kotlin.test.assertEquals

class CalendarDatesAcceptanceTest: AcceptanceTest() {
    @Test
    fun fetchAListOfCalendarDates() {
        val response = makeRequest("GET", "/calendar_dates")
        assertEquals(200, response.status)

        val body = response.readEntity(CalendarDateListResult::class.java)
        assertEquals(168, body.data.size)
    }
}
