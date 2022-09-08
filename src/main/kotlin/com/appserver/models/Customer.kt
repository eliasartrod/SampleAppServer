package com.appserver.models

import kotlinx.serialization.Serializable

/*@Serializable
data class Customer(val contents: List<CustomerDetails>)*/

@Serializable
data class Customer(val id: String, val firstName: String, val lastName: String, val email: String)

/** To not complicate the code, for this tutorial, we'll be using an in-memory storage (i.e. a mutable list of Customers) –
 * in a real application, we would be storing this information in a database so that it doesn't get lost after restarting our application.
 * We can add this line right after the data class declaration in Customer.kt file:*/
val customerStorage = mutableListOf(
    Customer("100","Jane","Smith","randomEmail@yahoo.com"),
    Customer("200","Eddie","Bejar","eddie.bejar95@gmail.com"),
    Customer("300","Ely", "Rodriguez", "eliasartrod@gmail.com")
)
/*
val customerStorage = listOf(
    Customer(
        listOf(
            CustomerDetails("100", "Jane", "Smith", "jane.smith@company.com"),
            CustomerDetails("200", "John", "Smith", "john.smith@company.com"),
            CustomerDetails("300", "Mary", "Smith", "mary.smith@company.com"),
        )
    )
)*/
