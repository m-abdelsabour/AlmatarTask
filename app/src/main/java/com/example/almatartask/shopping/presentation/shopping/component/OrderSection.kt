package com.example.almatartask.shopping.presentation.shopping.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.almatartask.shopping.domain.util.OrderType
import com.example.almatartask.shopping.domain.util.ShoppingOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    shoppingOrder: ShoppingOrder = ShoppingOrder.ItemName(OrderType.Descending),
    onOrderChange: (ShoppingOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Name" ,
                selected = shoppingOrder is ShoppingOrder.ItemName,
                onSelect = { onOrderChange(ShoppingOrder.ItemName(shoppingOrder.orderType)) }
            )

            Spacer(modifier = Modifier.width(4.dp))

            DefaultRadioButton(
                text = "Description" ,
                selected = shoppingOrder is ShoppingOrder.ItemDescription,
                onSelect = { onOrderChange(ShoppingOrder.ItemDescription(shoppingOrder.orderType)) }
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