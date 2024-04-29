package com.example.almatartask.shopping.presentation.util

sealed class Screen(val route:String){
    data object ShoppingScreen:Screen("shopping_screen")
    data object AddEditShoppingScreen:Screen("add_edit_shopping_screen")
}
