package dev.redfox.productlistlokal.domain.repositories

import dev.redfox.productlistlokal.data.models.ProductListModel
import dev.redfox.productlistlokal.domain.retrofit.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiInterface: ApiInterface) {
    suspend fun getProducts(): Response<ProductListModel> {
        return apiInterface.getProducts()
    }
}