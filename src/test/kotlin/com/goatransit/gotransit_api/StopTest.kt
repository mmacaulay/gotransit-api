package com.goatransit.gotransit_api

import io.dropwizard.jackson.Jackson
import io.dropwizard.testing.FixtureHelpers.fixture
import org.junit.Test
import kotlin.test.assertEquals

class StopTest {
    val mapper = Jackson.newObjectMapper()
    @Test
    fun testStopSerialization() {
        val stop = Stop(
                id = "WH-ENT-10043",
                name = "Whitby GO-Brock St S",
                lat = 43.8656,
                lon = -78.9361,
                zoneId = "test_zone_id",
                url = "http://google.ca",
                locationType = 2,
                parentStation = "whtbyS-parent",
                wheelchairBoarding = 1)
        val expected = mapper.writeValueAsString(mapper.readValue(fixture("fixtures/stop.json"), Stop::class.java))
        val actual = mapper.writeValueAsString(stop)
        assertEquals(expected, actual)
    }

    @Test
    fun testStopDeserialization() {
        val expected = Stop(
                id = "WH-ENT-10043",
                name = "Whitby GO-Brock St S",
                lat = 43.8656,
                lon = -78.9361,
                zoneId = "test_zone_id",
                url = "http://google.ca",
                locationType = 2,
                parentStation = "whtbyS-parent",
                wheelchairBoarding = 1)
        val actual = mapper.readValue(fixture("fixtures/stop.json"), Stop::class.java)
        assertEquals(expected, actual)
    }
}
