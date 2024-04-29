package com.example.almatartask.shopping.presentation.shopping

import com.example.almatartask.shopping.domain.model.Shopping
import com.example.almatartask.shopping.domain.util.OrderType
import com.example.almatartask.shopping.domain.util.ShoppingOrder

data class ShoppingState(
    val shopping: List<Shopping> = emptyList(),
    val shoppingOrder: ShoppingOrder = ShoppingOrder.NotBought(OrderType.Descending),
)
