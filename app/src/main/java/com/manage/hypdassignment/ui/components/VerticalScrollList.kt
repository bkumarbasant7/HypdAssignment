package com.manage.hypdassignment.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.manage.hypdassignment.ui.listOfProductData
import com.manage.hypdassignment.ui.state.ProductUiState

@Preview
@Composable
fun VerticalScrollListPreview() {
    VerticalScrollList(Modifier.fillMaxWidth(), data = listOfProductData, {}, false)
}

@Composable
fun VerticalScrollList(
    modifier: Modifier,
    data: List<ProductUiState>,
    onProductSelect: (id: String) -> Unit,
    selectUI: Boolean
) {
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(data) { _, item ->
            ProductItem(data = item, onProductSelect, selectUI)
        }
    }
}