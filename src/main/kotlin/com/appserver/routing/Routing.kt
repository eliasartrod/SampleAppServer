package com.appserver.routing

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*

fun Application.configureRouting() {

    /** Plugin, Called get function to declare an endpoint */
    routing {
        customerRouting()
        listOrdersRouting()
        getOrderRoute()
        totalizeOrderRoute()
        /** Static Content*/
        static {
            /** Can also call a .txt file or .jpg file and even in a package by calling the package name*/
            resource("facebook.html")
        }
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
