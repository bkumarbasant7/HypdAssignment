package com.manage.hypdassignment.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manage.hypdassignment.data.CollectionRepository
import com.manage.hypdassignment.ui.components.ProductState
import com.manage.hypdassignment.ui.state.CollectionUIState
import com.manage.hypdassignment.ui.state.ProductUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class DataState(
    val data1: CollectionUIState,
    val data2: List<ProductUiState>
)

class MainViewModel(private val repository: CollectionRepository) : ViewModel() {
    val collectionUiState: MutableState<CollectionUIState?> = mutableStateOf(null)
    val catologueUiState: MutableState<List<ProductUiState>?> = mutableStateOf(null)
    val selectUI: MutableState<Boolean> = mutableStateOf(false)
    val selectedCount: MutableState<Int> = mutableStateOf(0)
    fun loadData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.loadCollectionData()
                repository.collectionUIStateObservable.collect { collection ->
                    collectionUiState.value = collection?.data1
                    catologueUiState.value = collection?.data2
                    selectedCount.value =
                        collection?.data2?.count { it.productState == ProductState.SELECTED } ?: 0
                }
            }
        }
    }

    fun selectProduct(it: String) {
        repository.selectOrUnselectProduct(it)
    }

    fun deleteSelectedProduct() {
        repository.deleteSelectedProduct()
    }

    fun cancelSelection() {
        repository.onCancel()
    }

    fun addAllProduct() {
        repository.addAllProduct()
    }

    fun addProduct(id: String) {
        repository.addProduct(id)
    }
}