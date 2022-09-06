package com.appserver.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    /** Plugin, Called get function to declare an endpoint */
    routing {
        route(path = "/get/api/v1/httpMethod", method = HttpMethod.Get) {
            handle {
                call.respondText("Test Routing Path")
            }
        }
        get("/") {
            call.respondText("Hello World!")
        }
        get("/helloWorldVersionTwo") {
            call.respondText { "Hello World! Version 2" }
        }
        get("/get/api/v1/users/{username}") {
            val username = call.parameters["username"]
            //Extracting a Header
            val header = call.request.headers["Connection"]
            //Custom Header
            if (username == "Admin") {
                call.response.header(name = "CustomHeader", "Admin")
                call.respond(message = "Hello Admin", status = HttpStatusCode.OK)
            }
            call.respondText("Greetings, $username with header: $header")
        }
    }

    /** Installs Plugins into our Ktor Project */
    /*install(Routing) {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/book") {
            call.respondText { "Hello World! Version 2" }
        }
    }*/
}
