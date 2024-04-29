package com.example.almatartask.shopping.presentation.shopping.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.almatartask.shopping.domain.util.OrderType
import com.example.almatartask.shopping.domain.util.ShoppingOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    shoppingOrder: ShoppingOrder = ShoppingOrder.NotBought(OrderType.Descending),
    onOrderChange: (ShoppingOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Not Bought" ,
                selected = shoppingOrder is ShoppingOrder.NotBought,
                onSelect = { onOrderChange(ShoppingOrder.NotBought(shoppingOrder.orderType)) }
            )

            Spacer(modifier = Modifier.width(4.dp))

            DefaultRadioButton(
                text = "Bought" ,
                selected = shoppingOrder is ShoppingOrder.Bought,
                onSelect = { onOrderChange(ShoppingOrder.Bought(shoppingOrder.orderType)) }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            DefaultRadioButton(
                text = "Ascending" ,
                selected = shoppingOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(shoppingOrder.copy(OrderType.Ascending)) }
            )

            Spacer(modifier = Modifier.width(12.dp))
            DefaultRadioButton(
                text = "Descending" ,
                selected = shoppingOrder.orderType is OrderType.Descending,
                onSelect = { onOrderChange(shoppingOrder.copy(OrderType.Descending)) }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrderSectionPreview(){
    OrderSection (onOrderChange = {})
}