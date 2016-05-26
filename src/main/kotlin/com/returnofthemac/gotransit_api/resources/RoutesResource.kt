package com.returnofthemac.gotransit_api.resources

import com.codahale.metrics.annotation.Timed
import com.returnofthemac.gotransit_api.gtfs.Route
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

data class RouteListResult(var data: List<Route> = emptyList())

@Path("/routes")
@Produces(MediaType.APPLICATION_JSON)
class RoutesResource(val routes: List<Route>) {
    @GET
    @Timed
    fun list(@QueryParam("type") type: Int?): Response {
        val routeList = if (type != null) {
            routes.filter { r -> r.type == type }
        } else {
            routes
        }

        return Response.ok(RouteListResult(routeList)).build()
    }
}
