package com.returnofthemac.gotransit_api.gtfs

import org.junit.Test
import kotlin.test.assertEquals

class ParserTest {
    val parser = Parser()

    // Stops
    @Test
    fun testParsesListOfStops() {
        val stops = parser.parse<Stop>("/gtfs-data/stops.txt")
        assertEquals(1648, stops.size)
    }

    @Test
    fun testParsesAStop() {
        val stops = parser.parse<Stop>("/gtfs-data/stops.txt")
        val first = stops[0]
        //WH-ENT-10043,Whitby GO-Brock St S,43.8656,-78.9361,,,2,whtbyS-parent,0
        assertEquals("WH-ENT-10043", first.id)
        assertEquals("Whitby GO-Brock St S", first.name)
        assertEquals(43.8656, first.lat)
        assertEquals(-78.9361, first.lon)
        assertEquals(null, first.zoneId)
        assertEquals(null, first.url)
        assertEquals(2, first.locationType)
        assertEquals("whtbyS-parent", first.parentStation)
        assertEquals(0, first.wheelchairBoarding)
    }

    // Routes
    @Test
    fun testParsesListOfRoutes() {
        val routes = parser.parse<Route>("/gtfs-data/routes.txt")
        assertEquals(50, routes.size)
    }

    @Test
    fun testParsesARoute() {
        val routes = parser.parse<Route>("/gtfs-data/routes.txt")
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
