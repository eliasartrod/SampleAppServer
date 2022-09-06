package com.appserver.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

/** configureSerialization() is a function defined in plugins/Serialization.kt, which installs ContentNegotiation and enables the json serializer:*/
fun Application.configureSerialization(){
    install(ContentNegotiation) {
        json()
    }
}