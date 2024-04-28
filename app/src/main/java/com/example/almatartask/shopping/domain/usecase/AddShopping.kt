package com.example.almatartask.shopping.domain.usecase

import com.example.almatartask.shopping.domain.model.InvalidShoppingException
import com.example.almatartask.shopping.domain.model.Shopping
import com.example.almatartask.shopping.domain.repository.ShoppingRepository
import javax.inject.Inject

class AddShopping @Inject constructor(
    private val repository: ShoppingRepository
) {

    @Throws(InvalidShoppingException::class)
    suspend operator fun invoke(shopping: Shopping){
        if (shopping.itemName.toString().isBlank()){
            throw InvalidShoppingException("The name of the shopping can't be empty.")
        }
        if (shopping.description.toString().isBlank()){
            throw InvalidShoppingException("The content of the shopping can't be empty.")
        }
        repository.insertShopping(shopping)
    }

}