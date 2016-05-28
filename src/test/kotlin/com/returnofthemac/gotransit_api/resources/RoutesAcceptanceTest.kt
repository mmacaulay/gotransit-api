package com.returnofthemac.gotransit_api.resources

import org.glassfish.jersey.client.JerseyClientBuilder
import org.junit.Test
import kotlin.test.assertEquals

class RoutesAcceptanceTest: AcceptanceTest() {
    @Test
    fun fetchAListOfRoutes() {
        val client = JerseyClientBuilder().build()
        val response = client.target(String.format("http://localhost:%d/routes", RULE.localPort))
            .request()
            .get()

        assertEquals(200, response.status)

        val body = response.readEntity(RouteListResult::class.java)
        assertEquals(50, body.data.size)
    }

    @Test
    fun filterOnType() {
        val client = JerseyClientBuilder().build()
        val response = client.target(String.format("http://localhost:%d/routes?type=2", RULE.localPort))
                .request()
                .get()

        assertEquals(200, response.status)

        val body = response.readEntity(RouteListResult::class.java)
        assertEquals(7, body.data.size)
    }
}
