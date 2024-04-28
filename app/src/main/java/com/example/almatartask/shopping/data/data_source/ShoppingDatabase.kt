package com.example.almatartask.shopping.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalShopping::class],
    version = 2,
    exportSchema = false
)

abstract class ShoppingDatabase : RoomDatabase() {

    abstract val dao: ShoppingDao
}