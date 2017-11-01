package com.goatransit.gotransit_api

import com.univocity.parsers.annotations.Parsed

data class Route (
    @Parsed(field = "route_id")
    var id: String = "",

    @Parsed(field = "agency_id")
    var agencyId: String? = null,

    @Parsed(field = "route_short_name")
    var shortName: String = "",

    @Parsed(field = "route_long_name")
    var longName: String = "",

    @Parsed(field = "route_type")
    var type: Int = -1,

    @Parsed(field = "route_color")
    var colour: String? = null,

    @Parsed(field = "route_text_color")
    var textColour: String? = null
)

