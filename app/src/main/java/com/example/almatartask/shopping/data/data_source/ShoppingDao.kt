package com.example.almatartask.shopping.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {
    @Query("SELECT * FROM shopping")
     fun getAllShopping() : Flow<List<LocalShopping>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShopping(shopping: LocalShopping)

    @Query("SELECT * FROM shopping WHERE id = :id")
    suspend fun getShoppingById(id: Int): LocalShopping

    @Delete
    suspend fun deleteShopping(shopping: LocalShopping)

    @Update
    suspend fun updateShopping(shopping: LocalShopping)

}