package com.goatransit.gotransit_api

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration

class AppConfig() : Configuration() {
    @JsonProperty var appName: String = "gotransit-api"
}




