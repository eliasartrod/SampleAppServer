Important Notes:

SERVERS:
4 Servers Currently Supported by Ktor:
    1) Netty
        -DEPENDENCY:
            implementation("io.ktor:ktor-server-netty:$ktor_version")
    2) Jetty
        -DEPENDENCY:
            implementation("io.ktor:ktor-server-jetty":$ktor_version")
    3) Tomcat
        -DEPENDENCY:
            implementation("io.ktor:ktor-server-tomcat:$ktor_version")
    4) CIO (Co-Routine Based)
        -DEPENDENCY:
            implementation("io.ktor:ktor-server-cio:$ktor_version")
    5) FOR TESTING ONLY: Test Engine
        -DEPENDENCY:
            implementation("io.ktor:ktor-server-test-host:$ktor_version")

These servers can be created and ran in two ways:
    1) Embedded Server
        -NOTE: We do not need a separate conf. file here because we define that configuration in the
        parameters of the embedded server function:
            fun main() {
                embeddedServer(Netty, port = 8080) {
                    ...
                }.start(wait = true)
            }
    2) Engine Main
        -Application.conf (HOCON Format) - Human-Optimized Config Object Notation

MODULES:

We can use multiple modules to call a different endpoint such as:

    fun Application.configureRouting() {

        routing {
            get("/") {
                call.respondText("Some Text!")
            }
            get("/book") {
                call.respondText { "Some Text! Version 2" }
            }
        }
    }

    OR
    fun Application.moduleTwo() {
        routing {
            get("/endpoint") {
                call.respondText { "Some Text! Version 2" }
            }
        }
    }
    THEN calling it inside the application configuration file:
    application {
            modules = [ com.appserver.ApplicationKt.module,
                        com.appserver.ApplicationKt.moduleTwo ]
        }

PLUGINS:
What is a Plugin in a context of ktor framework?
    - A certain functionality which we can use out of the box inside a ktor project.
        Different Plugins Available:
            1) Serialization
            2) Encoding
            3) HTTP Headers
            4) Cookies
            5) Logging
            7) Auth
            ETC...

Structure:
    Basic:
    Client -> Request -> Routing -> App Logic -> Server -> Response -> Client
    Routing Plugins:
    Client -> Request -> Routing -> Plugin 2 -> Handler -> Plugin 3 -> Server -> Response -> Client

    Example:

    //Installs Plugins into our Ktor Project
    fun Application.configureRouting() {
    install(Routing) {
            get("/") {
                call.respondText("Hello World!")
            }
            get("/book") {
                call.respondText { "Hello World! Version 2" }
            }
        }
    }

    //Installs Logging Plugins in our Ktor Project, and if an endpoint is called, the event will be logged as a TRACE and an HTTP log.
    fun Application.configureRouting() {
    install(CallLogging)
        get("/") {
            call.respondText("Hello World!")
        }
    }

Testing Endpoints with POSTMAN:
    Instead of opening up a browser and typing in your "localhost:8080/someEndpoint" everytime, we can create a collection inside Postman
    for endpoint testing with ease.

    Download Link for Windows 64-bit:
    https://dl.pstmn.io/download/latest/win64

    Download Link for Mac (Intel Chip):
    https://dl.pstmn.io/download/latest/osx_64

    Download Link for Mac (Apple Chip):
    https://dl.pstmn.io/download/latest/osx_arm64

    Download Link for Linux:
    https://dl.pstmn.io/download/latest/linux64

        -Once POSTMAN has been downloaded, you can create an environment with a baseURL (endpoint baseURL link) and re-use that
            variable inside your endpoint collection:

            //Local Environment
            VARIABLE:      localhost or baseURL like http://store-inventory-api.svc.stageEnvironment
            TYPE:          default
            INITIAL VALUE: localhost:8080/someEndpoint

            //Inside your collection
            "GET"
            {{localhost}}/someEndpoint
            "GET"
            {{baseURL}}/someEndpoint