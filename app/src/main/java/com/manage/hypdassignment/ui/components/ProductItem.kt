package com.manage.hypdassignment.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.manage.hypdassignment.ui.productUiState
import com.manage.hypdassignment.ui.state.ProductUiState
import com.manage.hypdassignment.R
import com.manage.hypdassignment.ui.theme.*


@Preview
@Composable
fun ProductItemPreview() {
    ProductItem(productUiState, {}, false)
}

enum class ProductState {
    ADD, ADDED, UNSELECT, SELECTED
}

@Composable
fun ProductItem(
    data: ProductUiState,
    onProductActionClicked: (id: String) -> Unit,
    selectUI: Boolean
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .width(width = 165.dp)
    ) {
        Box {
            Card(
                modifier = Modifier.size(width = 165.dp, height = 215.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                AsyncImage(
                    model = data.productImage,
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
            }

            IconButton(modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(y = 20.dp), onClick = {
                onProductActionClicked(data.id)
            }) {
                if (selectUI) {
                    Image(
                        painter = if (data.productState == ProductState.ADDED || data.productState == ProductState.UNSELECT) {
                            painterResource(id = R.drawable.ic_product_checkbox)
                        } else painterResource(id = R.drawable.ic_select_product),
                        contentDescription = ""
                    )
                } else {
                    if (data.productState != ProductState.ADDED)
                        Image(
                            painter = when (data.productState) {
                                ProductState.ADD ->
                                    painterResource(id = R.drawable.ic_add_prod_to_store)
                                ProductState.UNSELECT -> painterResource(id = R.drawable.ic_product_checkbox)
                                ProductState.SELECTED -> painterResource(id = R.drawable.ic_select_product)
                                else -> painterResource(id = R.drawable.ic_add_prod_to_store)
                            },
                            contentDescription = ""
                        )
                }

            }

        }
        Text(modifier = Modifier.padding(vertical = 2.dp), text = data.name, style = boldTextStyle)
        Text(data.productDescription, style = mediumTextStyle, maxLines = 2)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(data.offerPrice, style = bigBoldTextStyle)
            Text(
                data.mrp,
                style = mediumTextStyle,
                textDecoration = TextDecoration.LineThrough,
                color = disableColor
            )
            Text("(${data.offInPercent}%)", style = boldTextStyle, color = active_positiveColor)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_discount_shape),
                contentDescription = "",
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color = dark_purple)
            )
            Text(stringResource(R.string.commission), style = boldTextStyle, color = dark_purple)
            Text("${data.commissionInPercent}%", style = boldTextStyle, color = dark_purple)
        }
    }

}