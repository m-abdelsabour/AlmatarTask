package com.example.almatartask.shopping.domain.util

sealed class ShoppingOrder(val orderType: OrderType) {
    class ItemName(orderType: OrderType) : ShoppingOrder(orderType)
    class ItemDescription(orderType: OrderType) : ShoppingOrder(orderType)

    fun copy(orderType: OrderType): ShoppingOrder {
        return when (this) {
            is ItemName -> ItemName(orderType)
            is ItemDescription -> ItemDescription(orderType)
        }
    }
}
