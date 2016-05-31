package com.returnofthemac.gotransit_api

import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.returnofthemac.gotransit_api.resources.healthchecks.CalendarDatesHealthCheck
import com.returnofthemac.gotransit_api.resources.healthchecks.RoutesHealthCheck
import com.returnofthemac.gotransit_api.resources.healthchecks.StopsHealthCheck
import com.returnofthemac.gotransit_api.resources.healthchecks.TripsHealthCheck
import com.returnofthemac.gotransit_api.resources.*
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment


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

        val routes = parser.parse<Route>("/gtfs-data/routes.txt")
        routesResources(environment, routes)

        val stops = parser.parse<Stop>("/gtfs-data/stops.txt")
        stopsResources(environment, stops)

        val trips = parser.parse<Trip>("/gtfs-data/trips.txt")
        tripsResources(environment, trips)

        val calendarDates = parser.parse<CalendarDate>("/gtfs-data/calendar_dates.txt")
        calendarDatesResources(environment, calendarDates)
    }
}


