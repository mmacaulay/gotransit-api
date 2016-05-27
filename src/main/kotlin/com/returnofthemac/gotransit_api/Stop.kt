package com.returnofthemac.gotransit_api

import com.univocity.parsers.annotations.Parsed

data class Stop (
    @Parsed(field = "stop_id")
    var id: String = "",

    @Parsed(field = "stop_name")
    var name: String = "",

    @Parsed(field = "stop_lat")
    var lat: Double = 0.0,

    @Parsed(field = "stop_lon")
    var lon: Double = 0.0,

    @Parsed(field = "zone_id")
    var zoneId: String? = null,

    @Parsed(field = "stop_url")
    var url: String? = null,

    @Parsed(field = "location_type")
    var locationType: Int? = null,

    @Parsed(field = "parent_station")
    var parentStation: String? = null,

    @Parsed(field = "wheelchair_boarding")
    var wheelchairBoarding: Int? = null
)

