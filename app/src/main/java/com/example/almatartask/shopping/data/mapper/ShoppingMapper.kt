package com.example.almatartask.shopping.data.mapper

import com.example.almatartask.shopping.data.data_source.LocalShopping
import com.example.almatartask.shopping.domain.model.Shopping


fun LocalShopping.toShopping() = Shopping(
    id = id,
    quantity = quantity,
    itemName = itemName,
    description = description
)

fun Shopping.toLocalShopping() = LocalShopping(
    id = id,
    quantity = quantity,
    itemName = itemName,
    description = description
)