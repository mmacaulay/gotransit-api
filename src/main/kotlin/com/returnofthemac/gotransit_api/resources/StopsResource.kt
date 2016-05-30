package com.returnofthemac.gotransit_api.resources

import com.codahale.metrics.annotation.Timed
import com.returnofthemac.gotransit_api.Stop
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

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
