package com.example.almatartask.shopping.domain.usecase

import com.example.almatartask.shopping.domain.model.Shopping
import com.example.almatartask.shopping.domain.repository.ShoppingRepository
import com.example.almatartask.shopping.domain.util.OrderType
import com.example.almatartask.shopping.domain.util.ShoppingOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetShoppingList (
    private val repository: ShoppingRepository
) {

    operator fun invoke(
        shoppingOrder: ShoppingOrder = ShoppingOrder.ItemName(OrderType.Descending)
    ): Flow<List<Shopping>> {
        return repository.getShoppingItems().map { list ->
            when (shoppingOrder.orderType) {
                is OrderType.Ascending -> {
                    when (shoppingOrder) {
                        is ShoppingOrder.ItemName -> list.sortedBy { it.itemName?.lowercase() }
                        is ShoppingOrder.ItemDescription -> list.sortedBy { it.description?.lowercase() }
                    }
                }

                is OrderType.Descending -> {
                    when (shoppingOrder) {
                        is ShoppingOrder.ItemName -> list.sortedByDescending { it.itemName?.lowercase() }
                        is ShoppingOrder.ItemDescription -> list.sortedByDescending { it.description?.lowercase() }
                    }
                }
            }
        }
    }

}