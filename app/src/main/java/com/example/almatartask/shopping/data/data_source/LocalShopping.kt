package com.example.almatartask.shopping.data.data_source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping")
data class LocalShopping(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val quantity: Int? = null,
    val itemName: String? = null,
    val description: String? = null,
    val bought: Boolean = false
)


