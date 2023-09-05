package dev.redfox.productlistlokal.data.models

import com.google.gson.annotations.SerializedName

data class ProductListModel(
    @SerializedName("products")
    val productListModel: ProductListModel?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("limit")
    val limit: Int?
)