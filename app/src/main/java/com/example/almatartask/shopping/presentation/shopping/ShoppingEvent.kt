package com.example.almatartask.shopping.presentation.shopping

import com.example.almatartask.shopping.domain.model.Shopping
import com.example.almatartask.shopping.domain.util.ShoppingOrder

sealed class ShoppingEvent {
    data class Order(val shoppingOrder: ShoppingOrder) : ShoppingEvent()
    data class DeleteShopping(val shopping: Shopping) : ShoppingEvent()
    data class UpdateShopping(val shopping: Shopping) : ShoppingEvent()
}