package dev.redfox.productlistlokal.domain.retrofit

import dev.redfox.productlistlokal.data.models.ProductListModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    suspend fun getProducts(): Response<ProductListModel>
}