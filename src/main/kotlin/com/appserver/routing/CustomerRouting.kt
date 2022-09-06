package com.appserver.routing

import com.appserver.models.Customer
import com.appserver.models.customerStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting() {
    //customer endpoint
    route("/customer") {
        /** To list all customers, we can return the customerStorage list by using the call.respond function in Ktor,
         * which can take a Kotlin object and return it serialized in a specified format. For the get handler, it looks like this:*/
        get {
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.OK)
            }
        }
        /** In Ktor, paths can also contain parameters that match specific path segments.
         * We can access their value using the indexed access operator (call.parameters["myParamName"]).
         * Let's add the following code to the get("{id?}") entry:*/
        get("{id?}") {
            val id = call.parameters["id"] ?:
                return@get call.respondText("Missing id", status = HttpStatusCode.BadRequest)

            val customer = customerStorage.find { it.id == id } ?:
                return@get call.respondText("No customer with id $id", status = HttpStatusCode.NotFound)
                    call.respond(customer)
        }
        /** Next, we implement the option for a client to POST a JSON representation of a client object,
         * which then gets put into our customer storage. Its implementation looks like this:*/
        post {
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }
        /** The implementation for deleting a customer follows a similar procedure as we have used for listing a specific customer.
         * We first get the id and then modify our customerStorage accordingly:*/
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (customerStorage.removeIf { it.id == id }) {
                call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}