package com.example.almatartask.shopping.presentation.add_edit_shopping

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.almatartask.di.MainDispatcher
import com.example.almatartask.shopping.domain.model.InvalidShoppingException
import com.example.almatartask.shopping.domain.model.Shopping
import com.example.almatartask.shopping.domain.usecase.ShoppingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditShoppingViewModel @Inject constructor(
    private val shoppingUseCases: ShoppingUseCases,
    savedStateHandle: SavedStateHandle?,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _shoppingTitle = mutableStateOf(
        ShoppingTextFieldState(
            hint = "Enter title..."
        )
    )
     var shoppingTitle: State<ShoppingTextFieldState> = _shoppingTitle

    private val _shoppingContent = mutableStateOf(
        ShoppingTextFieldState(
            hint = "Enter some content"
        )
    )
     val shoppingContent: State<ShoppingTextFieldState> = _shoppingContent

    private val _shoppingQuantity = mutableStateOf(
        ShoppingTextFieldState(
            hint = "Enter some quantity"
        )
    )
     val shoppingQuantity: State<ShoppingTextFieldState> = _shoppingQuantity


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentShoppingId: Int? = null

    init {
        savedStateHandle?.get<Int>("shoppingId")?.let { shoppingId ->
            if (shoppingId != -1) {
                viewModelScope.launch(dispatcher) {
                    shoppingUseCases.getShoppingItem(shoppingId).also {
                        currentShoppingId = it.id
                        _shoppingTitle.value = _shoppingTitle.value.copy(
                            text = it.itemName.toString(),
                            isHintVisible = false
                        )
                        _shoppingContent.value = _shoppingContent.value.copy(
                            text = it.description.toString(),
                            isHintVisible = false
                        )
                        _shoppingQuantity.value = _shoppingQuantity.value.copy(
                            text = it.quantity.toString(),
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditShoppingEvent) {
        when (event) {
            is AddEditShoppingEvent.EnteredTitle -> {
                _shoppingTitle.value = _shoppingTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditShoppingEvent.ChangeTitleFocus -> {
                _shoppingTitle.value = shoppingTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && shoppingTitle.value.text.isBlank()
                )
            }

            is AddEditShoppingEvent.EnteredContent -> {
                _shoppingContent.value = shoppingContent.value.copy(
                    text = event.value
                )
            }
            is AddEditShoppingEvent.ChangeContentFocus -> {
                _shoppingContent.value = shoppingContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && shoppingContent.value.text.isBlank()
                )
            }

            is AddEditShoppingEvent.EnteredQuantity -> {
                _shoppingQuantity.value = shoppingQuantity.value.copy(
                    text = event.value
                )
            }
            is AddEditShoppingEvent.ChangeQuantityFocus -> {
                _shoppingQuantity.value = shoppingQuantity.value.copy(
                    isHintVisible = !event.focusState.isFocused && shoppingQuantity.value.text.isBlank()
                )
            }

            is AddEditShoppingEvent.SaveShopping -> {
                viewModelScope.launch {
                    try {
                        shoppingUseCases.addShopping(
                            Shopping(
                                itemName = _shoppingTitle.value.text,
                                description = _shoppingContent.value.text,
                                quantity = _shoppingQuantity.value.text.toIntOrNull(),
                                id = currentShoppingId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveShopping)
                    } catch (e: InvalidShoppingException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't Save Shopping"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data object SaveShopping : UiEvent()
    }
}