package com.returnofthemac.gotransit_api

import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.returnofthemac.gotransit_api.GTFSParser
import com.returnofthemac.gotransit_api.Route
import com.returnofthemac.gotransit_api.Stop
import com.returnofthemac.gotransit_api.healthchecks.RoutesHealthCheck
import com.returnofthemac.gotransit_api.healthchecks.StopsHealthCheck
import com.returnofthemac.gotransit_api.resources.RoutesResource
import com.returnofthemac.gotransit_api.resources.StopsResource


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
        val parser = GTFSParser()

        // Routes
        val routes = parser.parse<Route>("/gtfs-data/routes.txt")

        val routesHealthCheck = RoutesHealthCheck(routes)
        environment.healthChecks().register("routes", routesHealthCheck)

        val routesResource = RoutesResource(routes)
        environment.jersey().register(routesResource)

        // Stops
        val stops = parser.parse<Stop>("/gtfs-data/stops.txt")

        val stopsHealthCheck = StopsHealthCheck(stops)
        environment.healthChecks().register("stops", stopsHealthCheck)

        val stopsResource = StopsResource(stops)
        environment.jersey().register(stopsResource)
    }
}


