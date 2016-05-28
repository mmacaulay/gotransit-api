package com.returnofthemac.gotransit_api.resources

import org.glassfish.jersey.client.JerseyClientBuilder
import org.junit.Test
import kotlin.test.assertEquals

class StopsAcceptanceTest: AcceptanceTest() {
    @Test
    fun fetchAListOfStops() {
        val client = JerseyClientBuilder().build()
        val response = client.target(String.format("http://localhost:%d/stops", RULE.localPort))
                .request()
                .get()

        assertEquals(200, response.status)

        val body = response.readEntity(StopListResult::class.java)
        assertEquals(1648, body.data.size)
    }
}
