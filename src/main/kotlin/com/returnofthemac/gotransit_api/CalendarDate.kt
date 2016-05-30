package com.returnofthemac.gotransit_api

import com.univocity.parsers.annotations.Parsed

data class CalendarDate (
        @Parsed(field = "service_id")
        var serviceId: String = "",

        @Parsed(field = "date")
        var date: String = "",

        @Parsed(field = "exception_type")
        var exceptionType: Int = 0
)

