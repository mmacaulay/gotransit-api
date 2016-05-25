package com.returnofthemac.gotransit_api.resources

import com.codahale.metrics.annotation.Timed
import com.returnofthemac.gotransit_api.gtfs.Route
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

data class RouteListResult(val data: List<Route>)

@Path("/routes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class RoutesResource(val routes: List<Route>) {
    @GET
    @Timed
    fun routes(): RouteListResult {
        return RouteListResult(routes)
    }
}
