package com.example.almatartask.shopping.presentation.add_edit_shopping

data class ShoppingTextFieldState(
    var text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)