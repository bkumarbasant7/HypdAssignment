package com.manage.hypdassignment.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manage.hypdassignment.R
import com.manage.hypdassignment.ui.theme.*

@Preview
@Composable
fun TopActionBarPreview() {
    TopActionBar(0, true, {}, {}) {

    }
}

@Composable
fun TopActionBar(
    selectedCount: Int,
    selectUI: Boolean,
    onNavigateUp: () -> Unit,
    onSelectEnabled: () -> Unit,
    onCancel: () -> Unit
) {
    Row(
        Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (selectUI) {
            Text(
                text = "Selected ($selectedCount)",
                style = largeBoldTextStyle,
                color = dark_purple
            )
            TextButton(onClick = {
                onCancel()
            }) {
                Text(text = "Cancel", style = mediumBigTextStyle, color = text_field_grey)
            }
        } else {
            IconButton(onClick = onNavigateUp) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_backarrow),
                    contentDescription = "Navigation"
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Select",
                    style = mediumBigTextStyle,
                    color = dark_purple,
                    modifier = Modifier.clickable {
                        onSelectEnabled()
                    })
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more_vert),
                        contentDescription = "Navigation"
                    )

                }
            }
        }
    }
}