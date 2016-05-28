package com.returnofthemac.gotransit_api.resources

import com.returnofthemac.gotransit_api.App
import com.returnofthemac.gotransit_api.AppConfig
import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import org.junit.ClassRule

open class AcceptanceTest {
    companion object {
        @JvmField
        @ClassRule
        val RULE = DropwizardAppRule<AppConfig>(App::class.java, ResourceHelpers.resourceFilePath("config_test.yml"))
    }
}
