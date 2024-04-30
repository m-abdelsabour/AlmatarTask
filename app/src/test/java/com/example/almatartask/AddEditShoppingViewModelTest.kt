package com.example.almatartask

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.example.almatartask.shopping.domain.usecase.ShoppingUseCases
import com.example.almatartask.shopping.presentation.add_edit_shopping.AddEditShoppingEvent
import com.example.almatartask.shopping.presentation.add_edit_shopping.AddEditShoppingViewModel
import com.example.almatartask.shopping.presentation.add_edit_shopping.ShoppingTextFieldState
import io.mockk.Runs
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class AddEditShoppingViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun `test onEvent for EnteredTitle event`() = scope.runTest {
        // Create mock dependencies
        val shoppingUseCases = mockk<ShoppingUseCases>()

        // Mock the state variables
        val shoppingTitleMock = mockk<MutableState<ShoppingTextFieldState>>(relaxed = true)

        every { shoppingTitleMock.value } returns ShoppingTextFieldState(text = "New Title")

        // Create the event
        val event = AddEditShoppingEvent.EnteredTitle("New Title")

        // Create an instance of the ViewModel using the mock dependencies
        val viewModel = AddEditShoppingViewModel(shoppingUseCases, null,dispatcher)

        viewModel.shoppingTitle=shoppingTitleMock

        viewModel.onEvent(event)

        assertEquals("New Title", shoppingTitleMock.value.text)
        assertEquals("", shoppingTitleMock.value.hint)
        assertEquals(true, shoppingTitleMock.value.isHintVisible)
    }
}