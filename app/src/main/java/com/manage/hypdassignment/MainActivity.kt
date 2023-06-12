package com.manage.hypdassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.manage.hypdassignment.ui.collectionData
import com.manage.hypdassignment.ui.listOfProductData
import com.manage.hypdassignment.ui.state.CollectionUIState
import com.manage.hypdassignment.ui.state.ProductUiState
import com.manage.hypdassignment.data.CollectionRepository
import com.manage.hypdassignment.ui.components.*
import com.manage.hypdassignment.ui.theme.*
import com.manage.hypdassignment.ui.viewModelFactoey.MainViewModelFactory
import com.manage.hypdassignment.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(CollectionRepository())
        )[MainViewModel::class.java]
        viewModel.loadData()
        setContent {
            HypdAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(
                        viewModel.collectionUiState.value,
                        viewModel.catologueUiState.value,
                        viewModel.selectUI.value,
                        viewModel.selectedCount.value,
                        onSelectUI = {
                            viewModel.selectUI.value = true
                        },
                        onProductAdded = {
                            viewModel.addProduct(it)
                        },
                        onAllProductAdded = {
                            viewModel.addAllProduct()
                        },
                        onCancel = {
                            viewModel.cancelSelection()
                            viewModel.selectUI.value = false

                        },
                        onProductSelected = {
                            viewModel.selectProduct(it)
                        },
                        onDeleteSelected = {
                            viewModel.deleteSelectedProduct()
                            viewModel.selectUI.value = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    collectData: CollectionUIState?,
    catalogue: List<ProductUiState>?,
    selectUI: Boolean,
    selectedCount: Int,
    onSelectUI: () -> Unit,
    onProductAdded: (id: String) -> Unit,
    onProductSelected: (id: String) -> Unit,
    onAllProductAdded: () -> Unit,
    onCancel: () -> Unit,
    onDeleteSelected: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopActionBar(selectedCount = selectedCount, selectUI, onNavigateUp = {
        }, {
            onSelectUI()
        }) {
            onCancel()
        }
        val outer = rememberScrollState()
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(outer)
        ) {
            if (!selectUI)
                CollectionView(data = collectData ?: collectionData)
            if (!selectUI && catalogue?.any { it.productState == ProductState.ADD } == true)
                Column(
                    modifier = Modifier
                        .background(backgroundColor)
                        .padding(16.dp)
                ) {
                    Row {
                        Text(
                            modifier = Modifier.weight(3f),
                            text = stringResource(R.string.add_product_title),
                            style = largeBoldTextStyle,
                            color = dark_purple
                        )
                        TextButton(modifier = Modifier.weight(1f), onClick = {
                            onAllProductAdded()
                        }) {
                            Text(
                                textAlign = TextAlign.Center,
                                text = stringResource(R.string.add_all),
                                style = bigBoldTextStyle,
                                color = primary_orange
                            )
                        }
                    }
                    HorizontalScrollList(data = catalogue.filter { it.productState == ProductState.ADD },
                        onProductAdded)
                }


            if (catalogue?.any { it.productState != ProductState.ADD } == true)
                VerticalScrollList(
                    modifier = Modifier
                        .height(800.dp)
                        .padding(16.dp),
                    data = catalogue?.filter { it.productState != ProductState.ADD } ?: emptyList(),
                    onProductSelected,
                    selectUI)

        }
        if (selectUI) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    border = BorderStroke(1.dp, active_negativeColor),
                    shape = RoundedCornerShape(12.dp),
                    onClick = { onDeleteSelected() }) {
                    Row() {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_trash),
                            tint = active_negativeColor,
                            contentDescription = ""
                        )
                        Text(
                            text = stringResource(R.string.delete_product),
                            style = bigMediumTextStyle,
                            color = active_negativeColor
                        )
                    }

                }
                Button(
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = primary_orange),
                    onClick = { }) {
                    Row() {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_collection),
                            contentDescription = "",
                            tint = Color.White
                        )
                        Text(
                            text = stringResource(R.string.copy_to_collection),
                            style = bigMediumTextStyle,
                            color = Color.White
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HypdAssignmentTheme {
        MainScreen(collectionData, listOfProductData, false, 0, {}, {}, {}, {}, {}, {})
    }
}