package com.example.almatartask.shopping.domain.usecase

data class ShoppingUseCases(
    val getShoppingList: GetShoppingList,
    val deleteShopping: DeleteShopping,
    val addShopping: AddShopping,
    val getShoppingItem: GetShoppingItem,
    val updateShopping: UpdateShopping
)
