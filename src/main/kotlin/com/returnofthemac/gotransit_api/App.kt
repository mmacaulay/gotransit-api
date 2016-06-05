package com.returnofthemac.gotransit_api

import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.returnofthemac.gotransit_api.resources.calendarDatesResources
import com.returnofthemac.gotransit_api.resources.routesResources
import com.returnofthemac.gotransit_api.resources.stopsResources
import com.returnofthemac.gotransit_api.resources.tripsResources
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
        val stops = parser.parse<Stop>("/gtfs-data/stops.txt")
        val trips = parser.parse<Trip>("/gtfs-data/trips.txt")
        val calendarDates = parser.parse<CalendarDate>("/gtfs-data/calendar_dates.txt")
        val stopTimes = parser.parse<StopTime>("/gtfs-data/stop_times.txt")

        routesResources(environment, routes)
        stopsResources(environment, stops, calendarDates, trips, stopTimes)
        tripsResources(environment, trips)
        calendarDatesResources(environment, calendarDates)
    }
}


