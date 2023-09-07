package dev.redfox.productlistlokal.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dev.redfox.productlistlokal.data.models.Product
import dev.redfox.productlistlokal.databinding.ProductDetailsBottomSheetBinding
import dev.redfox.productlistlokal.presentation.adapters.ProductImagesAdapter

@AndroidEntryPoint
class ProductDetailsBottomSheet(val product: Product): BottomSheetDialogFragment() {

    companion object {
        const val TAG = "ProductDetailsBottomSheet"
    }

    lateinit var binding: ProductDetailsBottomSheetBinding
    private lateinit var productImagesAdapter: ProductImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val lInflater = LayoutInflater.from(requireContext())
        binding = ProductDetailsBottomSheetBinding.inflate(lInflater)



        binding.apply {

            product.apply {
                tvProductTitle.text = this.title
                tvDescription.text = this.description
                tvProductPrice.text = "Price: ${this.price}"
                tvDiscountPercent.text = this.discountPercentage.toString()
                tvRating.text = "Rating: ${this.rating}"
                tvStock.text = this.stock.toString()
                tvProductBrand.text = this.brand
                tvCategory.text = this.category

                initRecyclerView(this.images)
            }
        }

        return binding.root
    }

    private fun initRecyclerView( imageList: List<String>){
        productImagesAdapter = ProductImagesAdapter(imageList)
        binding.apply {
            imageRecyclerView.setHasFixedSize(true)
            imageRecyclerView.adapter = productImagesAdapter
            imageRecyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL, false)
        }
    }
}