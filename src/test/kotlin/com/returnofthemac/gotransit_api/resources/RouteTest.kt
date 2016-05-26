package com.returnofthemac.gotransit_api.resources

import com.returnofthemac.gotransit_api.gtfs.Route
import io.dropwizard.jackson.Jackson
import io.dropwizard.testing.FixtureHelpers.fixture
import org.junit.Test
import kotlin.test.assertEquals

class RouteTest {
    val mapper = Jackson.newObjectMapper()
    @Test
    fun testRouteSerialization() {
        val route = Route(
                id = "237-ST",
                agencyId = "GO",
                colour = "794500",
                shortName = "ST",
                longName = "Stouffville",
                textColour = "FFFFFF",
                type = 2)
        val expected = mapper.writeValueAsString(mapper.readValue(fixture("fixtures/route.json"), Route::class.java))
        val actual = mapper.writeValueAsString(route)
        assertEquals(expected, actual)
    }

    @Test
    fun testRouteDeserialization() {
        val expected = Route(
                id = "237-ST",
                agencyId = "GO",
                colour = "794500",
                shortName = "ST",
                longName = "Stouffville",
                textColour = "FFFFFF",
                type = 2)
        val actual = mapper.readValue(fixture("fixtures/route.json"), Route::class.java)
        assertEquals(expected, actual)
    }
}
