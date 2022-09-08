package com.appserver.routing

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import kotlinx.html.*

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
            resource("static")
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
        /** Templating */
        get("/welcome") {
            val name = call.request.queryParameters["name"]
            call.respondHtml {
                head {
                    title { +"Custom Title" }
                }
                body {
                    if(name.isNullOrEmpty()) {
                        h3 { +"No name found." }
                    } else {
                        h3 { +"Welcome, $name" }
                    }
                    p { +"Current directory is: ${System.getProperty("user.dir")}" }
                    img (src = "static/androidlogo.jpg")
                }
            }
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
