package com.goatransit.gotransit_api.resources

import com.codahale.metrics.annotation.Timed
import com.returnofthemac.gotransit_api.CalendarDate
import com.returnofthemac.gotransit_api.Stop
import com.returnofthemac.gotransit_api.StopTime
import com.returnofthemac.gotransit_api.Trip
import com.returnofthemac.gotransit_api.resources.healthchecks.StopsHealthCheck
import io.dropwizard.setup.Environment
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

fun stopsResources (environment: Environment, stops: List<Stop>, calendarDates: List<CalendarDate>,
                    trips: List<Trip>, stopTimes: List<StopTime>) {
    val stopsHealthCheck = StopsHealthCheck(stops)
    environment.healthChecks().register("stops", stopsHealthCheck)

    val stopsResource = StopsResource(stops)
    environment.jersey().register(stopsResource)

    val stopDestinationsResource = StopDestinationsResource(stops, calendarDates, trips, stopTimes)
    environment.jersey().register(stopDestinationsResource)
}

data class StopListResult(var data: List<Stop> = emptyList())

@Path("/stops")
@Produces(MediaType.APPLICATION_JSON)
class StopsResource(val stops: List<Stop>) {
    @GET
    @Timed
    fun list(@QueryParam("name") name: String?): Response {
        val stopList = if (name != null) {
            stops.filter { stop -> stop.name.startsWith(name, true) }
        } else {
            stops
        }
        return Response.ok(StopListResult(stopList)).build()
    }
}

@Path("/destinations/{id}/date/{date}")
@Produces(MediaType.APPLICATION_JSON)
class StopDestinationsResource(val stops: List<Stop>, val calendarDates: List<CalendarDate>,
                               val trips: List<Trip>, val stopTimes: List<StopTime>) {
    @GET
    @Timed
    fun list(@PathParam("id") id: String, @PathParam("date") date: String): Response {
        val startingStop = stops.find { s -> s.id == id }
                ?: return Response.status(404).build()

        val calendarDates = calendarDates.filter { c -> c.date == date }
        if (calendarDates.size == 0) {
            return Response.ok(StopListResult(emptyList())).build()
        }

        val tripsForStopOnDate = trips.filter { t ->
            calendarDates.any { cd -> cd.serviceId == t.serviceId } &&
            stopTimes.any { st -> st.tripId == t.id && st.stopId == startingStop.id }
        }
        if (tripsForStopOnDate.size == 0) {
            return Response.ok(StopListResult(emptyList())).build()
        }
        val tripIds = tripsForStopOnDate.map { t -> t.id }
        val destinationStops: List<Stop> = stopTimes
                .filter { st -> tripIds.contains(st.tripId) && st.stopId != startingStop.id }
                .map { st -> stops.find { s -> s.id == st.stopId } }
                .fold(listOf<Stop>()) { stops, stop -> if (stop != null) stops.plus(stop) else stops }

        return Response.ok(StopListResult(destinationStops)).build()
    }
}
