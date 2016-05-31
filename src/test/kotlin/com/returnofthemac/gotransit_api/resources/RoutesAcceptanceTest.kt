package com.returnofthemac.gotransit_api.resources

import org.junit.Test
import kotlin.test.assertEquals

class RoutesAcceptanceTest: AcceptanceTest() {
    @Test
    fun fetchAListOfRoutes() {
        val response = makeRequest("GET", "/routes")
        assertEquals(200, response.status)

        val body = response.readEntity(RouteListResult::class.java)
        assertEquals(50, body.data.size)
    }

    @Test
    fun filterOnType() {
        val response = makeRequest("GET", "/routes?type=2")
        assertEquals(200, response.status)

        val body = response.readEntity(RouteListResult::class.java)
        assertEquals(7, body.data.size)
    }
}
