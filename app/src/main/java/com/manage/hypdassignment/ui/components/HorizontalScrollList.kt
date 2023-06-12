package com.manage.hypdassignment.ui.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.manage.hypdassignment.ui.listOfProductData
import com.manage.hypdassignment.ui.state.ProductUiState

@Preview
@Composable
fun HorizontalScrollListPreview() {
    HorizontalScrollList(listOfProductData) {}
}

@Composable
fun HorizontalScrollList(data: List<ProductUiState>, onProductAdded: (id: String) -> Unit) {
    LazyRow(reverseLayout = false) {
        itemsIndexed(data) { _, item ->
            ProductItem(data = item, onProductAdded, false)
        }
    }
}