package com.returnofthemac.gotransit_api.resources

import org.glassfish.jersey.client.JerseyClientBuilder
import org.junit.Test
import kotlin.test.assertEquals

class TripsAcceptanceTest: AcceptanceTest() {
    @Test
    fun fetchAListOfTrips() {
        val client = JerseyClientBuilder().build()
        val response = client.target(String.format("http://localhost:%d/trips", RULE.localPort))
                .request()
                .get()

        assertEquals(200, response.status)

        val body = response.readEntity(TripListResult::class.java)
        assertEquals(23, body.data.size)
    }
}
