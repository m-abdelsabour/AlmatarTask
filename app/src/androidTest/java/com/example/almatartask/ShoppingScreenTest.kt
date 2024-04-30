package com.example.almatartask

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.almatartask.DummyShoppingList.getDummyList
import com.example.almatartask.shopping.domain.util.OrderType
import com.example.almatartask.shopping.domain.util.ShoppingOrder
import com.example.almatartask.shopping.presentation.SemanticsDescription
import com.example.almatartask.shopping.presentation.shopping.ShoppingScreen
import com.example.almatartask.shopping.presentation.shopping.ShoppingState
import com.example.almatartask.shopping.presentation.util.Screen
import com.example.almatartask.ui.theme.AlmatarTaskTheme
import org.junit.Rule
import org.junit.Test

class ShoppingScreenTest {

    // to add function to existing class and work with composable ui and set content without need for host such activity or fragment
    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()


    @Test
    fun floatingState_isActive() {
        testRule.setContent {
            AlmatarTaskTheme {
                ShoppingScreen(state = ShoppingState(), onItemClick = {}) {}
            }
        }
        testRule.onNodeWithContentDescription(SemanticsDescription.SHOPPING_FLOATING_BUTTON)
            .assertIsDisplayed()
    }

    @Test
    fun loadingContentState_isActive() {
        val dummyList = getDummyList()

        testRule.setContent {

            AlmatarTaskTheme {
                ShoppingScreen(state = ShoppingState(
                    shopping = dummyList, shoppingOrder = ShoppingOrder.NotBought(
                        OrderType.Descending
                    )
                ), onItemClick = {}) {}
            }
        }

        testRule.onNodeWithText(dummyList[0].id.toString()).assertIsDisplayed()
    }
}