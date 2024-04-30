package com.example.almatartask.shopping.presentation.shopping

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.almatartask.shopping.presentation.SemanticsDescription
import com.example.almatartask.shopping.presentation.shopping.component.OrderSection
import com.example.almatartask.shopping.presentation.shopping.component.ShoppingItem

@Composable
fun ShoppingScreen(
    state: ShoppingState,
    onItemClick: (Int) -> Unit,
    event: (ShoppingEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onItemClick(-1) },
                contentColor = MaterialTheme.colorScheme.primary,
                // add semantics for ui test to ensure floating is active
                modifier = Modifier.semantics {
                    this.contentDescription=SemanticsDescription.SHOPPING_FLOATING_BUTTON
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Shopping")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Your Shopping List", style = MaterialTheme.typography.headlineLarge)
            if (state.shopping.isNotEmpty()) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                     shoppingOrder = state.shoppingOrder,
                    onOrderChange = {
                        event(ShoppingEvent.Order(it))
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            LazyColumn{
                items(state.shopping) { shopping ->
                        ShoppingItem(
                            shopping = shopping,
                            modifier = Modifier
                                .fillMaxWidth(),
                            onItemClick = {shopping.id?.let { onItemClick(it) }},
                            onDeleteClick = {
                                event(ShoppingEvent.DeleteShopping(shopping))
                            },
                            onSwitchClick = {
                                event(ShoppingEvent.UpdateShopping(shopping))
                            }
                        )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            if (state.shopping.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "You Don't Have Shopping\nPlease Add",
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }
            }
        }

    }
}