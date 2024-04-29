package com.example.almatartask.shopping.domain.usecase

import com.example.almatartask.shopping.domain.model.Shopping
import com.example.almatartask.shopping.domain.repository.ShoppingRepository
import com.example.almatartask.shopping.domain.util.OrderType
import com.example.almatartask.shopping.domain.util.ShoppingOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetShoppingList (
    private val repository: ShoppingRepository
) {

    operator fun invoke(
        shoppingOrder: ShoppingOrder = ShoppingOrder.NotBought(OrderType.Descending)
    ): Flow<List<Shopping>> {
        return repository.getShoppingItems().map { list ->
            when (shoppingOrder.orderType) {
                is OrderType.Ascending -> {
                    when (shoppingOrder) {
                        is ShoppingOrder.NotBought -> {
                            val sortedList = list.sortedBy { it.itemName?.lowercase() }
                            sortedList.filter { !it.bought }
                        }
                        is ShoppingOrder.Bought -> {
                           val sortedList= list.sortedBy { it.itemName?.lowercase() }
                            sortedList.filter { it.bought }
                        }
                    }
                }

                is OrderType.Descending -> {
                    when (shoppingOrder) {
                        is ShoppingOrder.NotBought -> {
                            val sortedList = list.sortedByDescending { it.itemName?.lowercase() }
                            sortedList.filter { !it.bought }
                        }
                        is ShoppingOrder.Bought -> {
                            val sortedList= list.sortedByDescending { it.itemName?.lowercase() }
                            sortedList.filter { it.bought }
                        }
                    }
                }
            }
        }
    }

}