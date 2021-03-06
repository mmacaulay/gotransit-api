package com.goatransit.gotransit_api.resources.healthchecks

import com.codahale.metrics.health.HealthCheck
import com.returnofthemac.gotransit_api.Trip

class TripsHealthCheck(val trips: List<Trip>): HealthCheck() {
    override fun check(): Result? {
        if (trips.size > 0) {
            return Result.healthy()
        } else {
            return Result.unhealthy("Empty trips list")
        }
    }
}
