package com.goatransit.gotransit_api

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class StopsParsingTests {
    val parser = GTFSParser()
    var stops: List<Stop> = emptyList()

    @Before
    fun beforeEach() {
        stops = parser.parse<Stop>("/gtfs-data/stops.txt")
    }

    @Test
    fun testParsesListOfStops() {
        assertEquals(1648, stops.size)
    }

    @Test
    fun testParsesAStop() {
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
}
