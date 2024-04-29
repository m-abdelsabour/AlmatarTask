package com.example.almatartask.shopping.domain.util

sealed class ShoppingOrder(val orderType: OrderType) {
    class NotBought(orderType: OrderType) : ShoppingOrder(orderType)
    class Bought(orderType: OrderType) : ShoppingOrder(orderType)

    fun copy(orderType: OrderType): ShoppingOrder {
        return when (this) {
            is NotBought -> NotBought(orderType)
            is Bought -> Bought(orderType)
        }
    }
}
