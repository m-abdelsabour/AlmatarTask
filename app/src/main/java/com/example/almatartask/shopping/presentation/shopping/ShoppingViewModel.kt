package com.example.almatartask.shopping.presentation.shopping

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.almatartask.di.MainDispatcher
import com.example.almatartask.shopping.domain.usecase.ShoppingUseCases
import com.example.almatartask.shopping.domain.util.OrderType
import com.example.almatartask.shopping.domain.util.ShoppingOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val stateHandel: SavedStateHandle,
    private val shoppingUseCases: ShoppingUseCases,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _state by mutableStateOf(ShoppingState())

    val state: State<ShoppingState> = derivedStateOf { _state }

    init {
        getShoppingList(ShoppingOrder.NotBought(OrderType.Descending))
    }

    fun onEvent(event: ShoppingEvent) {
        when (event) {
            is ShoppingEvent.Order -> {
                if (_state.shoppingOrder::class == event.shoppingOrder::class && _state.shoppingOrder.orderType == event.shoppingOrder.orderType) {
                    return
                }
                getShoppingList(event.shoppingOrder)
            }
            is ShoppingEvent.DeleteShopping -> {
                viewModelScope.launch(dispatcher) {
                    shoppingUseCases.deleteShopping(event.shopping)
                }
            }
            is ShoppingEvent.UpdateShopping -> {
                viewModelScope.launch(dispatcher) {
                    event.shopping.bought = !event.shopping.bought
                    shoppingUseCases.addShopping(event.shopping)
                    getShoppingList(ShoppingOrder.NotBought(OrderType.Descending))
                }
            }
        }
    }

    private fun getShoppingList(shoppingOrder: ShoppingOrder) {
        viewModelScope.launch(dispatcher) {
            shoppingUseCases.getShoppingList.invoke(shoppingOrder).collect { shopping ->
                    _state = _state.copy(
                        shopping = shopping,
                        shoppingOrder = shoppingOrder
                    )
            }
        }
    }

}