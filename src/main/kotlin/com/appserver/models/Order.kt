package com.appserver.models

import kotlinx.serialization.Serializable

@Serializable
data class Order(val number: String, val id: String, val contents: List<OrderItem>)

@Serializable
data class OrderItem(val item: String, val amount: Int, val price: Double)

/** We also once again need a place to store our orders. To skip having to define a POST route –
 * something you're more than welcome to attempt on your own using the knowledge from the Customer routes –
 * we will prepopulate our orderStorage with some sample orders.
 * We can again define it as a top-level declaration inside the Order.kt file.*/
val orderStorage = listOf(Order("2020-04-06-01", "100", listOf(
        OrderItem("Ham Sandwich", 2, 5.50),
        OrderItem("Water", 1, 1.50),
        OrderItem("Beer", 3, 2.30),
        OrderItem("Cheesecake", 1, 3.75)
    )),
    Order("2020-04-03-01", "200", listOf(
        OrderItem("Cheeseburger", 1, 8.50),
        OrderItem("Water", 2, 1.50),
        OrderItem("Coke", 2, 1.76),
        OrderItem("Ice Cream", 1, 2.35)
    )),
    Order("2020-04-05-01", "200", listOf(
        OrderItem("Cheeseburger", 1, 8.50),
        OrderItem("Water", 3, 1.50),
        OrderItem("Coke", 6, 1.76),
        OrderItem("Ice Cream", 1, 2.35)
    ))
)