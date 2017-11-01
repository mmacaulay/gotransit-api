package com.goatransit.gotransit_api.resources

import com.codahale.metrics.annotation.Timed
import com.returnofthemac.gotransit_api.Route
import com.returnofthemac.gotransit_api.resources.healthchecks.RoutesHealthCheck
import io.dropwizard.setup.Environment
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

fun routesResources (environment: Environment, routes: List<Route>) {
    val routesHealthCheck = RoutesHealthCheck(routes)
    environment.healthChecks().register("routes", routesHealthCheck)

    val routesResource = RoutesResource(routes)
    environment.jersey().register(routesResource)
}

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
