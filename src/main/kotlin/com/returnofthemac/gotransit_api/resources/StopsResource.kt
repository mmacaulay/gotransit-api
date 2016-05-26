package com.returnofthemac.gotransit_api.resources

import com.codahale.metrics.annotation.Timed
import com.returnofthemac.gotransit_api.gtfs.Stop
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

data class StopListResult(var data: List<Stop> = emptyList())

@Path("/stops")
@Produces(MediaType.APPLICATION_JSON)
class StopsResource(val stops: List<Stop>) {
    @GET
    @Timed
    fun list(): Response {
        return Response.ok(StopListResult(stops)).build()
    }
}
