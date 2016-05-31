package com.returnofthemac.gotransit_api.resources

import com.codahale.metrics.annotation.Timed
import com.returnofthemac.gotransit_api.Stop
import com.returnofthemac.gotransit_api.resources.healthchecks.StopsHealthCheck
import io.dropwizard.setup.Environment
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

fun stopsResources (environment: Environment, stops: List<Stop>) {
    val stopsHealthCheck = StopsHealthCheck(stops)
    environment.healthChecks().register("stops", stopsHealthCheck)

    val stopsResource = StopsResource(stops)
    environment.jersey().register(stopsResource)
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

@Path("/stops/{id}/destinations")
@Produces(MediaType.APPLICATION_JSON)
class StopDestinationsResource(val stops: List<Stop>) {
    @GET
    @Timed
    fun list(@PathParam("id") id: String): Response {
        val stop = stops.find { s -> s.id == id }
        if (stop == null) {
            return Response.status(404).build()
        }

        return Response.ok().build()
    }
}
