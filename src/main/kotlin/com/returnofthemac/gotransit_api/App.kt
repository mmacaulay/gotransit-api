package com.returnofthemac.gotransit_api

import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.returnofthemac.gotransit_api.gtfs.Parser
import com.returnofthemac.gotransit_api.gtfs.Route
import com.returnofthemac.gotransit_api.healthchecks.RoutesHealthCheck
import com.returnofthemac.gotransit_api.resources.RoutesResource


class App() : Application<AppConfig>() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            App().run(*args)
        }
    }

    override fun initialize(bootstrap: Bootstrap<AppConfig>?) {
        super.initialize(bootstrap)
        bootstrap?.objectMapper?.registerKotlinModule()
    }

    override fun run(configuration: AppConfig, environment: Environment) {
        val parser = Parser()
        val routes = parser.parse<Route>("/gtfs-data/routes.txt")

        val routesHealthCheck = RoutesHealthCheck(routes)
        environment.healthChecks().register("routes", routesHealthCheck)

        val routesResource = RoutesResource(routes)
        environment.jersey().register(routesResource)
    }
}


