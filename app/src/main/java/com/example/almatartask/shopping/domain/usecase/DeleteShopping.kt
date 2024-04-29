package com.example.almatartask.shopping.domain.usecase

import com.example.almatartask.shopping.domain.model.InvalidShoppingException
import com.example.almatartask.shopping.domain.model.Shopping
import com.example.almatartask.shopping.domain.repository.ShoppingRepository
import javax.inject.Inject

class DeleteShopping (
    private val repository: ShoppingRepository
) {

    suspend operator fun invoke(shopping: Shopping){
        repository.deleteShopping(shopping)
    }

}