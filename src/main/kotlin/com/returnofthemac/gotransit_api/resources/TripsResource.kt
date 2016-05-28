package com.returnofthemac.gotransit_api.resources

import com.codahale.metrics.annotation.Timed
import com.returnofthemac.gotransit_api.Trip
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

data class TripListResult(var data: List<Trip> = emptyList())

@Path("/trips")
@Produces(MediaType.APPLICATION_JSON)
class TripsResource(val trips: List<Trip>) {
    @GET
    @Timed
    fun list(): Response {
        return Response.ok(TripListResult(trips)).build()
    }
}
