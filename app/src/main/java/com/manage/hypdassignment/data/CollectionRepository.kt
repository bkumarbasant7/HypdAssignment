package com.manage.hypdassignment.data

import com.manage.hypdassignment.ui.state.CollectionUIState
import com.manage.hypdassignment.ui.state.ProductUiState
import com.manage.hypdassignment.data.ApiDao.Companion.client
import com.manage.hypdassignment.data.request.SimilarProductRequest
import com.manage.hypdassignment.data.response.toProductUiState
import com.manage.hypdassignment.ui.components.ProductState
import com.manage.hypdassignment.ui.viewmodel.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CollectionRepository {
    private val networkCallScope = CoroutineScope(Dispatchers.IO)

    private val collectionUIState: MutableStateFlow<DataState?> = MutableStateFlow(null)
    val collectionUIStateObservable = collectionUIState

    fun loadCollectionData() {
        networkCallScope.launch {
            try {
                val response = client.getCollections()
                if (response.isSuccessful) {


                    collectionUIState.value = DataState(
                        CollectionUIState(
                            response.body()?.payload?.name ?: "NA",
                            response.body()?.payload?.catalog_ids?.size ?: 0,
                            response.body()?.payload?.default_image?.get(0)?.src ?: ""
                        ),
                        response.body()?.payload?.toProductUiState(ProductState.ADDED)
                            ?: emptyList()
                    )
                    loadSimilarProduct(response.body()?.payload?.catalog_ids ?: emptyList())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun loadSimilarProduct(ids: List<String>) {
        networkCallScope.launch {
            try {
                val response = client.getSimilarProduct(catalog_ids = SimilarProductRequest(ids))
                val old: MutableList<ProductUiState> =
                    collectionUIState.value?.data2?.toMutableList() ?: mutableListOf()
                if (response.isSuccessful) {
                    response.body()?.toProductUiState(ProductState.ADD)?.forEach {
                        old.add(it)
                    }
                    collectionUIState.value = collectionUIState.value?.copy(data2 = old)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addProduct(id: String) {
        val newCatalogueData = mutableListOf<ProductUiState>()
        for (i in collectionUIState.value?.data2 ?: emptyList()) {
            if (i.id == id) {
                newCatalogueData.add(i.copy(productState = ProductState.ADDED))
            } else {
                newCatalogueData.add(i)
            }
        }
        collectionUIState.value = collectionUIState.value?.copy(data2 = newCatalogueData)

    }

    fun addAllProduct() {
        val newCatalogueData = mutableListOf<ProductUiState>()
        for (i in collectionUIState.value?.data2 ?: emptyList()) {
            newCatalogueData.add(i.copy(productState = ProductState.ADDED))
        }
        collectionUIState.value = collectionUIState.value?.copy(data2 = newCatalogueData)
    }

    fun deleteSelectedProduct() {
        val newCatalogueData = mutableListOf<ProductUiState>()
        for (i in collectionUIState.value?.data2 ?: emptyList()) {
            if (i.productState == ProductState.SELECTED) {
                newCatalogueData.add(i.copy(productState = ProductState.ADD))
            } else newCatalogueData.add(i)
        }
        collectionUIState.value = collectionUIState.value?.copy(data2 = newCatalogueData)
    }

    fun onCancel() {
        val newCatalogueData = mutableListOf<ProductUiState>()
        for (i in collectionUIState.value?.data2 ?: emptyList()) {
            if (i.productState == ProductState.SELECTED) {
                newCatalogueData.add(i.copy(productState = ProductState.ADDED))
            } else {
                if (i.productState == ProductState.ADD)
                    newCatalogueData.add(i.copy(productState = ProductState.ADD))
                else
                    newCatalogueData.add(i.copy(productState = ProductState.ADDED))
            }
        }
        collectionUIState.value = collectionUIState.value?.copy(data2 = newCatalogueData)

    }

    fun selectOrUnselectProduct(it: String) {
        val data = collectionUIState.value?.data2 ?: emptyList()
        val newData = mutableListOf<ProductUiState>()
        for (i in data) {
            if (i.id == it) {
                if (i.productState == ProductState.UNSELECT || i.productState == ProductState.ADDED)
                    newData.add(i.copy(productState = ProductState.SELECTED))
                else newData.add(i.copy(productState = ProductState.UNSELECT))
            } else {
                newData.add(i)
            }
        }
        collectionUIState.value = collectionUIState.value?.copy(data2 = newData)
    }
}