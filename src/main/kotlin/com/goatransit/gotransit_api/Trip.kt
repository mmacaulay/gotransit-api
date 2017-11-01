package com.goatransit.gotransit_api

import com.univocity.parsers.annotations.Parsed

data class Trip(
    @Parsed(field = "trip_id")
    var id: String = "",

    @Parsed(field = "route_id")
    var routeId: String = "",

    @Parsed(field = "service_id")
    var serviceId: String = "",

    @Parsed(field = "trip_headsign")
    var tripHeadsign: String? = null,

    @Parsed(field = "trip_short_name")
    var tripShortName: String? = null,

    @Parsed(field = "direction_id")
    var directionId: Int? = null,

    @Parsed(field = "block_id")
    var blockId: String? = null,

    @Parsed(field = "shape_id")
    var shapeId: String? = null,

    @Parsed(field = "wheelchair_accessible")
    var wheelchairAccessible: Int? = null,

    @Parsed(field = "bikes_allowed")
    var bikesAllowed: Int? = null,

    @Parsed(field = "route_variant")
    var routeVariant: String? = null
)
