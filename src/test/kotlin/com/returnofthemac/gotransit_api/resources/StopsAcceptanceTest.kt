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
        val response = makeRequest("GET", "/destinations/bogus/date/20160531")
        assertEquals(404, response.status)
    }

    @Test
    fun stopDestinationsReturnsEmptyListForUnknownDate() {
        val response = makeRequest("GET", "/destinations/WH-ENT-10043/date/18670701")
        assertEquals(200, response.status)
        val body = response.readEntity(StopListResult::class.java)
        assertEquals(0, body.data.size)
    }

    @Test
    fun stopDestinationsReturnsEmptyListWhenNoTrips() {
        val response = makeRequest("GET", "/destinations/WH-ENT-10043/date/20160601")
        assertEquals(200, response.status)
        val body = response.readEntity(StopListResult::class.java)
        assertEquals(0, body.data.size)
    }

    @Test
    fun stopDestinationsReturnsAListOfDestinationStops() {
        val response = makeRequest("GET", "/destinations/UN/date/20160520")
        assertEquals(200, response.status)
        val body = response.readEntity(StopListResult::class.java)
        val destinations = body.data

        assertEquals(6, destinations.size)
        assertEquals("Danforth GO", destinations[0].name)
        assertEquals("Rouge Hill GO", destinations[1].name)
        assertEquals("Pickering GO", destinations[2].name)
        assertEquals("Ajax GO", destinations[3].name)
        assertEquals("Whitby GO", destinations[4].name)
        assertEquals("Oshawa GO", destinations[5].name)
    }
}
