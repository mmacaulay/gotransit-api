package com.returnofthemac.gotransit_api.healthchecks

import com.codahale.metrics.health.HealthCheck
import com.returnofthemac.gotransit_api.gtfs.Route

class RoutesHealthCheck(val routes: List<Route>): HealthCheck() {
    override fun check(): Result? {
        if (routes.size > 0) {
            return Result.healthy()
        } else {
            return Result.unhealthy("Empty routes list")
        }
    }
}
