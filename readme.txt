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
                call.respondText("Hello World!")
            }
            get("/book") {
                call.respondText { "Hello World! Version 2" }
            }
        }
    }

    OR
    fun Application.moduleTwo() {
        routing {
            get("/book") {
                call.respondText { "Hello World! Version 2" }
            }
        }
    }
    THEN calling it inside the application configuration file:
    application {
            modules = [ com.appserver.ApplicationKt.module,
                        com.appserver.ApplicationKt.moduleTwo ]
        }

PLUGINS:
