package com.manage.hypdassignment.ui.state

import com.manage.hypdassignment.ui.components.ProductState

data class ProductUiState(
    val id: String,
    val name: String,
    val productImage: String,
    val productDescription: String,
    val offerPrice: String,
    val mrp: String,
    val offInPercent: Int,
    val commissionInPercent: Int,
    val productState: ProductState = ProductState.ADD,
)
