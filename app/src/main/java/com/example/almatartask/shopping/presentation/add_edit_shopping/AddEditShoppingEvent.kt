package com.example.almatartask.shopping.presentation.add_edit_shopping

import androidx.compose.ui.focus.FocusState

sealed class AddEditShoppingEvent {

    data class EnteredTitle(val value: String) : AddEditShoppingEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditShoppingEvent()

    data class EnteredContent(val value: String) : AddEditShoppingEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditShoppingEvent()


    data class EnteredQuantity(val value: String) : AddEditShoppingEvent()
    data class ChangeQuantityFocus(val focusState: FocusState) : AddEditShoppingEvent()

    data object SaveShopping : AddEditShoppingEvent()

}

