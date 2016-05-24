package com.returnofthemac.gotransit_api.gtfs

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class StopsParserTest {
    var stops: List<Stop> = emptyList()
    @Before
    fun setup() {
        val parser = StopsParser("/gtfs-data/stops.txt")
        stops = parser.parse()
    }

    @Test
    fun testParsesListOfStops() {
        assertEquals(1648, stops.size, "Parses a list of stops")
    }

    @Test
    fun testParsesAStop() {
        val first = stops[0]
        //WH-ENT-10043,Whitby GO-Brock St S,43.8656,-78.9361,,,2,whtbyS-parent,0
        assertEquals(first.id, "WH-ENT-10043")
        assertEquals(first.name, "Whitby GO-Brock St S")
        assertEquals(first.lat, 43.8656)
        assertEquals(first.lon, -78.9361)
        assertEquals(first.zoneId, null)
        assertEquals(first.url, null)
        assertEquals(first.locationType, 2)
        assertEquals(first.parentStation, "whtbyS-parent")
        assertEquals(first.wheelchairBoarding, 0)
    }
}

