package com.returnofthemac.gotransit_api.resources

import org.junit.Test
import kotlin.test.assertEquals

class TripsAcceptanceTest: AcceptanceTest() {
    @Test
    fun fetchAListOfTrips() {
        val response = makeRequest("GET", "/trips")
        assertEquals(200, response.status)

        val body = response.readEntity(TripListResult::class.java)
        assertEquals(23, body.data.size)
    }
}
