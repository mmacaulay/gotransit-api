package com.returnofthemac.gotransit_api

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class StopTimesParsingTests {
    val parser = GTFSParser()
    var stopTimes: List<StopTime> = emptyList()

    @Before
    fun beforeEach() {
        stopTimes = parser.parse<StopTime>("/gtfs-data/stop_times.txt")
    }

    @Test
    fun testParsesListOfStopTimes() {
        assertEquals(30, stopTimes.size)
    }

    @Test
    fun testParsesAStopTime() {
        val first = stopTimes[0]
        //5429-Fri-65483,15:55:00,15:55:00,00081,7,0,0
        assertEquals("5429-Fri-65483", first.tripId)
        assertEquals("15:55:00", first.arrivalTime)
        assertEquals("15:55:00", first.departureTime)
        assertEquals("00081", first.stopId)
        assertEquals(7, first.stopSequence)
        assertEquals(0, first.pickupType)
        assertEquals(0, first.dropOffType)
    }
}
