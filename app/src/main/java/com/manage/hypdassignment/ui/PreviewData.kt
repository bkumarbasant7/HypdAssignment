package com.manage.hypdassignment.ui

import com.manage.hypdassignment.ui.state.CollectionUIState
import com.manage.hypdassignment.ui.state.ProductUiState

val collectionData = CollectionUIState("Saptarshi’s Summer Collection", 50, "")
val productUiState =
    ProductUiState("", "Puma", "", "Forest bomber jacket with Hoodie", "1,529", "2599", 15, 12)
val listOfProductData = listOf(
    ProductUiState(
        "Puma",
        "Puma", "", "Forest bomber jacket with Hoodie", "1,529", "2599", 15, 12
    ),
    ProductUiState("", "Puma", "", "Forest bomber jacket with Hoodie", "1,529", "2599", 15, 12),
)