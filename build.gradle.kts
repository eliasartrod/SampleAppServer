val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.10"
    //Kotlin-serialization
    kotlin("plugin.serialization").version("1.7.10")
    id("io.ktor.plugin") version "2.1.0"
}

group = "com.appserver"
version = "0.0.1"
application {
    /** Using Engine Main */
    //mainClass.set("io.ktor.server.netty.EngineMain")

    /** Using Embedded Server */
    mainClass.set("com.appserver.Application.kt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {

    /** ktor-server-core adds Ktor's core components to our project. */
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")

    /** ktor-server-netty adds the Netty engine to our project,
       allowing us to use server functionality without having to rely on an external application container */
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")

    /** logback-classic provides an implementation of SLF4J, allowing us to see nicely formatted logs in a console. */
    implementation("ch.qos.logback:logback-classic:$logback_version")

    /** ktor-server-content-negotiation and ktor-serialization-kotlinx-json provide a convenient
     * mechanism for converting Kotlin objects into a serialized form like JSON, and vice versa.
     * We will use it to format our APIs output, and to consume user input that is structured in JSON.*/
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    /** ktor-server-test-host and kotlin-test-junit allow us to test parts of our Ktor application without having to use
     * the whole HTTP stack in the process. We will use this to define unit tests for our project.*/
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}