package com.goatransit.gotransit_api.resources.healthchecks

import com.codahale.metrics.health.HealthCheck
import com.returnofthemac.gotransit_api.Route

class RoutesHealthCheck(val routes: List<Route>): HealthCheck() {
    override fun check(): Result? {
        if (routes.size > 0) {
            return Result.healthy()
        } else {
            return Result.unhealthy("Empty routes list")
        }
    }
}
