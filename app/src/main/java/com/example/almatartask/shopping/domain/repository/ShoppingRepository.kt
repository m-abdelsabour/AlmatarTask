package com.example.almatartask.shopping.domain.repository

import com.example.almatartask.shopping.domain.model.Shopping
import kotlinx.coroutines.flow.Flow

interface ShoppingRepository {

    fun getShoppingItems(): Flow<List<Shopping>>

    suspend fun getShoppingById(id: Int): Shopping

    suspend fun insertShopping(shopping: Shopping)

    suspend fun deleteShopping(shopping: Shopping)

    suspend fun updateShopping(shopping: Shopping)

}