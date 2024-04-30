package com.example.almatartask

import androidx.compose.animation.fadeIn
import com.example.almatartask.shopping.domain.model.Shopping


object DummyShoppingList {
    fun getDummyList() = arrayListOf<Shopping>(
        Shopping(
            id = 1,
            itemName = "first shopping",
            description = "n0",
            quantity = 6,
            bought = false
        ),
        Shopping(
            id = 2,
            itemName = "second shopping",
            description = "n2",
            quantity = 4,
            bought = false
        ),

    )
}