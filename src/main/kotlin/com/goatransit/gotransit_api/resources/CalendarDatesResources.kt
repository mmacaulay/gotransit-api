package com.goatransit.gotransit_api.resources

import com.codahale.metrics.annotation.Timed
import com.returnofthemac.gotransit_api.CalendarDate
import com.returnofthemac.gotransit_api.resources.healthchecks.CalendarDatesHealthCheck
import io.dropwizard.setup.Environment
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

fun calendarDatesResources (environment: Environment, calendarDates: List<CalendarDate>) {
    val calendarDatesHealthCheck = CalendarDatesHealthCheck(calendarDates)
    environment.healthChecks().register("calendar_dates", calendarDatesHealthCheck)

    val calendarDatesResource = CalendarDatesResource(calendarDates)
    environment.jersey().register(calendarDatesResource)
}

data class CalendarDateListResult(var data: List<CalendarDate> = emptyList())

@Path("/calendar_dates")
@Produces(MediaType.APPLICATION_JSON)
class CalendarDatesResource(val calendarDates: List<CalendarDate>) {
    @GET
    @Timed
    fun list(): Response {
        return Response.ok(CalendarDateListResult(calendarDates)).build()
    }
}
