package com.example.almatartask.shopping.data.repository

import com.example.almatartask.di.IoDispatcher
import com.example.almatartask.shopping.data.data_source.ShoppingDao
import com.example.almatartask.shopping.data.mapper.toLocalShopping
import com.example.almatartask.shopping.data.mapper.toShopping
import com.example.almatartask.shopping.domain.model.Shopping
import com.example.almatartask.shopping.domain.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShoppingRepositoryImpl(
    private val dao: ShoppingDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ShoppingRepository {
    override fun getShoppingItems(): Flow<List<Shopping>> {
        return dao.getAllShopping().map { it.map { shopping -> shopping.toShopping() } }
    }


    override suspend fun getShoppingById(id: Int): Shopping = withContext(dispatcher) {
        dao.getShoppingById(id).toShopping()
    }

    override suspend fun insertShopping(shopping: Shopping) {
        dao.insertShopping(shopping.toLocalShopping())
    }

    override suspend fun deleteShopping(shopping: Shopping) {
        dao.deleteShopping(shopping.toLocalShopping())
    }

    override suspend fun updateShopping(shopping: Shopping) {
        dao.updateShopping(shopping.toLocalShopping())
    }


}