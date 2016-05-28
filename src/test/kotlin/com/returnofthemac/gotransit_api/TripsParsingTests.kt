package com.returnofthemac.gotransit_api

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class TripsParsingTests {
    val parser = GTFSParser()
    var trips: List<Trip> = emptyList()

    @Before
    fun beforeEach() {
        trips = parser.parse<Trip>("/gtfs-data/trips.txt")
    }

    @Test
    fun testParsesListOfTrips() {
        assertEquals(23, trips.size)
    }

    @Test
    fun testParsesATrip() {
        val first = trips[0]
        assertEquals(1, first.bikesAllowed)
        assertEquals("32A", first.blockId)
        assertEquals(1, first.directionId)
        assertEquals("237-ST", first.routeId)
        assertEquals("ST", first.routeVariant)
        assertEquals("5445-Fri", first.serviceId)
        assertEquals("UIUN", first.shapeId)
        assertEquals("ST-Union Station", first.tripHeadsign)
        assertEquals("5445-Fri-873", first.id)
        assertEquals("873", first.tripShortName)
        assertEquals(1, first.wheelchairAccessible)
    }
}
