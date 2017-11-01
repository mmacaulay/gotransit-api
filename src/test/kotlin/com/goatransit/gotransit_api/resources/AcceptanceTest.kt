package com.goatransit.gotransit_api.resources

import com.returnofthemac.gotransit_api.App
import com.returnofthemac.gotransit_api.AppConfig
import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import org.glassfish.jersey.client.JerseyClientBuilder
import org.junit.ClassRule
import javax.ws.rs.core.Response

open class AcceptanceTest {
    companion object {
        @JvmField
        @ClassRule
        val RULE = DropwizardAppRule<AppConfig>(App::class.java, ResourceHelpers.resourceFilePath("config_test.yml"))

        fun makeRequest(method: String, path: String): Response {
            val client = JerseyClientBuilder().build()
            return client.target(String.format("http://localhost:%d%s", RULE.localPort, path))
                    .request()
                    .build(method)
                    .invoke()
        }
    }
}
