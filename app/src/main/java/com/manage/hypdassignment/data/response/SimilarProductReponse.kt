package com.manage.hypdassignment.data.response

import com.manage.hypdassignment.ui.state.ProductUiState
import com.manage.hypdassignment.ui.components.ProductState
import com.manage.hypdassignment.utils.getCurrencyFormat

data class SimilarProductResponse(
    val payload: List<SimilarProductPayload>,
    val success: Boolean
)

fun SimilarProductResponse.toProductUiState(state: ProductState): List<ProductUiState> {
    val data = mutableListOf<ProductUiState>()
    for (i in this.payload) {
        data.add(
            ProductUiState(
                i.id,
                i.name,
                i.featured_image.src,
                i.description,
                i.retail_price.value.getCurrencyFormat(i.retail_price.iso),
                i.base_price.value.getCurrencyFormat(i.base_price.iso),
                (((i.base_price.value.toDouble() - i.retail_price.value.toDouble()) / i.base_price.value) * 100).toInt(),
                12,
                state
            )
        )
    }
    return data
}

data class SimilarProductPayload(
    val base_price: BasePrice,
    val brand_id: String,
    val brand_info: BrandInfo,
    val catalog_content: List<String>,
    val category_lvl1: List<String>,
    val category_lvl2: List<String>,
    val category_lvl3: List<String>,
    val category_path: List<String>,
    val commission_rate: Int,
    val created_at: String,
    val description: String,
    val eta: Eta,
    val featured_image: FeaturedImage,
    val hsn_code: String,
    val id: String,
    val inventory_status: String,
    val keywords: List<String>,
    val lname: String,
    val name: String,
    val retail_price: RetailPrice,
    val slug: String,
    val specs: List<Spec>,
    val status: Status,
    val status_history: List<StatusHistory>,
    val tax: Tax,
    val transfer_price: TransferPrice,
    val updated_at: String,
    val variant_type: String,
    val variants: List<Variant>
)


//data class BasePrice(
//    val iso: String,
//    val value: Int
//)

data class BrandInfo(
    val id: String,
    val logo: Logo,
    val name: String,
    val username: String
)

data class Eta(
    val max: Int,
    val min: Int,
    val unit: String
)

//data class FeaturedImage(
//    val height: Int,
//    val src: String,
//    val width: Int
//)

//data class RetailPrice(
//    val iso: String,
//    val value: Int
//)

data class Spec(
    val name: String,
    val value: String
)

//data class Status(
//    val created_at: String,
//    val name: String,
//    val value: String
//)

data class StatusHistory(
    val created_at: String,
    val name: String,
    val value: String
)

data class Tax(
    val rate: Int,
    val type: String
)

data class TransferPrice(
    val iso: String,
    val value: Int
)

//data class Variant(
//    val attribute: String,
//    val easyecom_sku: String,
//    val easyecom_variant_id: String,
//    val id: String,
//    val inventory_id: String,
//    val inventory_info: InventoryInfo,
//    val is_deleted: Boolean,
//    val sku: String,
//    val unit_in_stock: Int
//)

data class Logo(
    val height: Int,
    val src: String,
    val width: Int
)

//data class InventoryInfo(
//    val catalog_id: String,
//    val created_at: String,
//    val id: String,
//    val updated_at: String,
//    val variant_id: String
//)