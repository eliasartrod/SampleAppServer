package com.appserver

import com.appserver.plugins.configureRouting
import com.appserver.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/** Using Engine Main */
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

/** Using Engine Main */
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSerialization()
}

/** Using Embedded Server */
/*
fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}*/


/** Using multiple modules without using the Routing.kt class */
/*fun Application.moduleTwo() {
    routing {
        get("/book") {
            call.respondText { "Hello World! Version 2" }
        }
    }
}*/

/** Using only Application.kt class, we can just call the module function without a Routing class */
/*fun Application.module() {
    routing {
        get("/") {
            call.respondText { "Hello, World!" }
        }
    }
}*/


