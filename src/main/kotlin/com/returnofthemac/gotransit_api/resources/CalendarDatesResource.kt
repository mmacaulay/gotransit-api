package com.returnofthemac.gotransit_api.resources

import com.codahale.metrics.annotation.Timed
import com.returnofthemac.gotransit_api.CalendarDate
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

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
