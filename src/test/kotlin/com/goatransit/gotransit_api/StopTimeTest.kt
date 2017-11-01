package com.goatransit.gotransit_api

import io.dropwizard.jackson.Jackson
import io.dropwizard.testing.FixtureHelpers.fixture
import org.junit.Test
import kotlin.test.assertEquals

class StopTimeTest {
    val mapper = Jackson.newObjectMapper()
    @Test
    fun testStopTimeSerialization() {
        val stopTime = StopTime(
                tripId = "5429-Fri-65483",
                arrivalTime = "15:55:00",
                departureTime = "15:55:00",
                stopId = "00081",
                stopSequence = 7,
                pickupType = 0,
                dropOffType = 0)
        val expected = mapper.writeValueAsString(mapper.readValue(fixture("fixtures/stopTime.json"), StopTime::class.java))
        val actual = mapper.writeValueAsString(stopTime)
        assertEquals(expected, actual)
    }

    @Test
    fun testStopTimeDeserialization() {
        val expected = StopTime(
                tripId = "5429-Fri-65483",
                arrivalTime = "15:55:00",
                departureTime = "15:55:00",
                stopId = "00081",
                stopSequence = 7,
                pickupType = 0,
                dropOffType = 0)
        val actual = mapper.readValue(fixture("fixtures/stopTime.json"), StopTime::class.java)
        assertEquals(expected, actual)
    }
}
