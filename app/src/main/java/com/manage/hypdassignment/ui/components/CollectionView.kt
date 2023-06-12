package com.manage.hypdassignment.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.manage.hypdassignment.ui.collectionData
import com.manage.hypdassignment.ui.state.CollectionUIState
import com.manage.hypdassignment.ui.theme.boldTextStyle
import com.manage.hypdassignment.ui.theme.dark_purple
import com.manage.hypdassignment.ui.theme.largeBoldTextStyle
import com.manage.hypdassignment.ui.theme.secondary_text

@Preview
@Composable
fun CollectionViewPreview() {
    CollectionView(data = collectionData)
}

@Composable
fun CollectionView(data: CollectionUIState) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .size(64.dp), shape = RoundedCornerShape(16.dp)
        ) {
            AsyncImage(
                data.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
        }
        Column(Modifier.fillMaxWidth()) {
            Text(text = data.collectionName, style = largeBoldTextStyle, color = dark_purple)
            Text(
                text = "${data.productCount} Products",
                style = boldTextStyle,
                color = secondary_text
            )
        }
    }
}