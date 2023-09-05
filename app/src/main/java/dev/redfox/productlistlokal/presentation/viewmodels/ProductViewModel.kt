package dev.redfox.productlistlokal.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.redfox.productlistlokal.data.models.ProductListModel
import dev.redfox.productlistlokal.domain.repositories.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

    private val _productResponse = MutableLiveData<Response<ProductListModel>>()
    val productResponse: MutableLiveData<Response<ProductListModel>> get() = _productResponse

    fun getProducts(){
        viewModelScope.launch {
            val pdtResponse = mainRepository.getProducts()
            _productResponse.value = pdtResponse
        }
    }
}