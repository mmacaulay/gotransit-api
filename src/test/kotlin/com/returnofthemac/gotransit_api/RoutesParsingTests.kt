package com.returnofthemac.gotransit_api

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class RoutesParsingTests {
    val parser = GTFSParser()
    var routes: List<Route> = emptyList()

    @Before
    fun before() {
        routes = parser.parse<Route>("/gtfs-data/routes.txt")
    }

    @Test
    fun testParsesListOfRoutes() {
        assertEquals(50, routes.size)
    }

    @Test
    fun testParsesARoute() {
        val first = routes[0]
        assertEquals("237-ST", first.id)
        assertEquals("GO", first.agencyId)
        assertEquals("ST", first.shortName)
        assertEquals("Stouffville", first.longName)
        assertEquals(2, first.type)
        assertEquals("794500", first.colour)
        assertEquals("FFFFFF", first.textColour)
    }
}
