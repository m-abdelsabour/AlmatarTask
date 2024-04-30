package com.example.almatartask

import com.example.almatartask.shopping.domain.usecase.ShoppingUseCases
import com.example.almatartask.shopping.domain.util.OrderType
import com.example.almatartask.shopping.domain.util.ShoppingOrder
import com.example.almatartask.shopping.presentation.shopping.ShoppingState
import com.example.almatartask.shopping.presentation.shopping.ShoppingViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun `getShoppingList updates state correctly`() = scope.runTest {
        // Create a mock of ShoppingUseCases
        val shoppingUseCases = mockk<ShoppingUseCases>()
        // Create a mock of the shopping list and shopping order
        val shoppingList = DummyShoppingList.getDummyList()
        val shoppingOrder = ShoppingOrder.NotBought(OrderType.Descending)
        // Mock the behavior of shoppingUseCases.getShoppingList
        coEvery { shoppingUseCases.getShoppingList(any()) } returns flowOf(shoppingList)
        // Create an instance of the ShoppingViewModel using the mock dependencies
        val viewModel = ShoppingViewModel(shoppingUseCases, dispatcher)
        // Invoke the function under test
        viewModel.getShoppingList(shoppingOrder)

        // Advance the dispatcher to allow coroutines to execute
        advanceUntilIdle()
        // Verify that the state is updated correctly
        val expectedState = ShoppingState(shopping = shoppingList, shoppingOrder = shoppingOrder)
        assertEquals(expectedState, viewModel.state.value)
    }
}

