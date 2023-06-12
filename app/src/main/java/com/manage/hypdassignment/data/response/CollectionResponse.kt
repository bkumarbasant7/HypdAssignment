package com.manage.hypdassignment.data.response

import com.manage.hypdassignment.ui.state.ProductUiState
import com.manage.hypdassignment.ui.components.ProductState
import com.manage.hypdassignment.utils.getCurrencyFormat

data class CollectionResponse(
    val payload: Payload,
    val success: Boolean
)

data class Payload(
    val catalog_ids: List<String>,
    val catalog_info: List<CatalogInfo>,
    val created_at: String,
    val default_image: List<DefaultImage>,
    val duplicate_id: String,
    val id: String,
    val image: String,
    val influencer_id: String,
    val influencer_info: InfluencerInfo,
    val name: String,
    val order: Int,
    val slug: String,
    val status: String,
    val updated_at: String
)

fun Payload.toProductUiState(state: ProductState): List<ProductUiState> {
    val data = mutableListOf<ProductUiState>()
    for (i in this.catalog_info) {
        data.add(
            ProductUiState(
                i.id,
                i.name,
                i.featured_image.src,
                "description",
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


data class CatalogInfo(
    val base_price: BasePrice,
    val brand_id: String,
    val discount_id: String,
    val featured_image: FeaturedImage,
    val id: String,
    val inventory_status: String,
    val name: String,
    val retail_price: RetailPrice,
    val variants: List<Variant>
)

data class DefaultImage(
    val height: Int,
    val src: String,
    val width: Int
)

class InfluencerInfo

data class BasePrice(
    val iso: String,
    val value: Int
)

data class FeaturedImage(
    val height: Int,
    val src: String,
    val width: Int
)

data class RetailPrice(
    val iso: String,
    val value: Int
)

data class Variant(
    val attribute: String,
    val easyecom_sku: String,
    val easyecom_variant_id: String,
    val id: String,
    val inventory_id: String,
    val inventory_info: InventoryInfo,
    val is_deleted: Boolean,
    val shopify_variant_id: String,
    val sku: String
)

data class InventoryInfo(
    val catalog_id: String,
    val created_at: String,
    val id: String,
    val sku: String,
    val status: Status,
    val unit_in_stock: Int,
    val updated_at: String,
    val variant_id: String
)

data class Status(
    val created_at: String,
    val value: String
)