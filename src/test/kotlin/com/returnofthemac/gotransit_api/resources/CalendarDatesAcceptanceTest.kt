package com.returnofthemac.gotransit_api.resources

import org.glassfish.jersey.client.JerseyClientBuilder
import org.junit.Test
import kotlin.test.assertEquals

class CalendarDatesAcceptanceTest: AcceptanceTest() {
    @Test
    fun fetchAListOfCalendarDates() {
        val client = JerseyClientBuilder().build()
        val response = client.target(String.format("http://localhost:%d/calendar_dates", RULE.localPort))
                .request()
                .get()

        assertEquals(200, response.status)

        val body = response.readEntity(CalendarDateListResult::class.java)
        assertEquals(168, body.data.size)
    }
}
