package com.returnofthemac.gotransit_api.healthchecks

import com.codahale.metrics.health.HealthCheck
import com.returnofthemac.gotransit_api.CalendarDate

class CalendarDatesHealthCheck(val calendarDates: List<CalendarDate>): HealthCheck() {
    override fun check(): Result? {
        if (calendarDates.size > 0) {
            return Result.healthy()
        } else {
            return Result.unhealthy("Empty calendar dates list")
        }
    }
}
