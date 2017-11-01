package com.goatransit.gotransit_api

import io.dropwizard.jackson.Jackson
import io.dropwizard.testing.FixtureHelpers.fixture
import org.junit.Test
import kotlin.test.assertEquals

class TripTest {
    val mapper = Jackson.newObjectMapper()
    @Test
    fun testTripSerialization() {
        val trip = Trip(
                id = "5445-Fri-873",
                routeId = "237-ST",
                serviceId = "5445-Fri",
                tripHeadsign = "ST-Union Station",
                tripShortName = "873",
                directionId = 1,
                blockId = "32A",
                shapeId = "UIUN",
                wheelchairAccessible = 1,
                bikesAllowed = 1,
                routeVariant = "ST")
        val expected = mapper.writeValueAsString(mapper.readValue(fixture("fixtures/trip.json"), Trip::class.java))
        val actual = mapper.writeValueAsString(trip)
        assertEquals(expected, actual)
    }

    @Test
    fun testTripDeserialization() {
        val expected = Trip(
                id = "5445-Fri-873",
                routeId = "237-ST",
                serviceId = "5445-Fri",
                tripHeadsign = "ST-Union Station",
                tripShortName = "873",
                directionId = 1,
                blockId = "32A",
                shapeId = "UIUN",
                wheelchairAccessible = 1,
                bikesAllowed = 1,
                routeVariant = "ST")
        val actual = mapper.readValue(fixture("fixtures/trip.json"), Trip::class.java)
        assertEquals(expected, actual)
    }
}
