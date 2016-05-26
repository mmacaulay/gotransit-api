package com.returnofthemac.gotransit_api.healthchecks

import com.codahale.metrics.health.HealthCheck
import com.returnofthemac.gotransit_api.gtfs.Stop

class StopsHealthCheck(val stops: List<Stop>): HealthCheck() {
    override fun check(): Result? {
        if (stops.size > 0) {
            return Result.healthy()
        } else {
            return Result.unhealthy("Empty stops list")
        }
    }
}
