package com.goatransit.gotransit_api

import com.univocity.parsers.annotations.Parsed

data class StopTime (
        @Parsed(field = "trip_id")
        var tripId: String = "",

        @Parsed(field = "arrival_time")
        var arrivalTime: String = "",

        @Parsed(field = "departure_time")
        var departureTime: String = "",

        @Parsed(field = "stop_id")
        var stopId: String = "",

        @Parsed(field = "stop_sequence")
        var stopSequence: Int = 0,

        @Parsed(field = "pickup_type")
        var pickupType: Int? = null,

        @Parsed(field = "drop_off_type")
        var dropOffType: Int? = null
)

