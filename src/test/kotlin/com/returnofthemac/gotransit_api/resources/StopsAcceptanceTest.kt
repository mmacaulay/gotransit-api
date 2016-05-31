package com.returnofthemac.gotransit_api.resources

import org.junit.Test
import kotlin.test.assertEquals

class StopsAcceptanceTest: AcceptanceTest() {
    // Stops

    @Test
    fun fetchAListOfStops() {
        val response = makeRequest("GET", "/stops")
        assertEquals(200, response.status)

        val body = response.readEntity(StopListResult::class.java)
        assertEquals(1648, body.data.size)
    }

    @Test
    fun filterByName() {
        val response = makeRequest("GET", "/stops?name=whitby")
        assertEquals(200, response.status)

        val body = response.readEntity(StopListResult::class.java)
        assertEquals(7, body.data.size)
        assertEquals("Whitby GO-Brock St S", body.data[0].name)
    }

    // Stop Destinations

    @Test
    fun stopDestinationsReturnsNotFound() {
        val response = makeRequest("GET", "/stops/bogus/destinations")
        assertEquals(404, response.status)
    }
}
