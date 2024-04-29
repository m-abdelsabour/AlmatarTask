package com.example.almatartask.shopping.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Shopping(
    val id: Int? = null,
    val quantity: Int? = null,
    val itemName: String? = null,
    val description: String? = null,
    var bought: Boolean = false
)
class InvalidShoppingException(message:String):Exception(message)


