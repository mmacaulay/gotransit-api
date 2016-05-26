package com.returnofthemac.gotransit_api.resources

import com.returnofthemac.gotransit_api.App
import com.returnofthemac.gotransit_api.AppConfig
import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import org.glassfish.jersey.client.JerseyClientBuilder
import org.junit.ClassRule
import org.junit.Test
import kotlin.test.assertEquals

class RoutesAcceptanceTest {
    companion object {
        @JvmField
        @ClassRule
        val RULE = DropwizardAppRule<AppConfig>(App::class.java, ResourceHelpers.resourceFilePath("config_test.yml"))
    }

    @Test
    fun fetchAListOfRoutes() {
        val client = JerseyClientBuilder().build()
        val response = client.target(String.format("http://localhost:%d/routes", RULE.localPort))
            .request()
            .get()

        assertEquals(200, response.status)

        val body = response.readEntity(RouteListResult::class.java)
        assertEquals(50, body.data.size)
    }

    @Test
    fun filterOnType() {
        val client = JerseyClientBuilder().build()
        val response = client.target(String.format("http://localhost:%d/routes?type=2", RULE.localPort))
                .request()
                .get()

        assertEquals(200, response.status)

        val body = response.readEntity(RouteListResult::class.java)
        assertEquals(7, body.data.size)
    }
}
