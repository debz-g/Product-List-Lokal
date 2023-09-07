package dev.redfox.productlistlokal.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.redfox.productlistlokal.R
import dev.redfox.productlistlokal.data.models.Product
import dev.redfox.productlistlokal.databinding.FragmentProductListBinding
import dev.redfox.productlistlokal.presentation.adapters.ProductListAdapter
import dev.redfox.productlistlokal.presentation.viewmodels.ProductViewModel
import java.util.Locale

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    //Binding
    private var _binding: FragmentProductListBinding? = null
    private val binding
        get() = _binding!!

    //ViewModel
    val productViewModel: ProductViewModel by viewModels()

    var productList = ArrayList<Product>()

    private lateinit var productListAdapter: ProductListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel.getProducts()
        initCliks()
        attachObserver()
    }

    private fun initCliks(){
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                filterList(newText)
                return true
            }

        })
    }

    private fun attachObserver(){
        productViewModel.productResponse.observe(viewLifecycleOwner, Observer {
            val productData: MutableList<Product> = it.body()!!.productListModel as MutableList<Product>

            productListAdapter = ProductListAdapter(productData)
            productListAdapter.notifyDataSetChanged()
            binding.searchRecyclerView.setHasFixedSize(true)
            binding.searchRecyclerView.adapter = productListAdapter
            binding.searchRecyclerView.layoutManager = LinearLayoutManager(context)

            productListAdapter.onItemClick = { product ->
                val dialog = ProductDetailsBottomSheet(product)
                dialog.isCancelable = true
                dialog.show(parentFragmentManager, ProductDetailsBottomSheet.TAG)
            }
        })
    }

//    private fun filterList(query: String?){
//        if(query!=null){
//            val filteredList = ArrayList<Product>()
//            for (i in productList){
//                if(i.title.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))){
//                    filteredList.add(i)
//                }
//            }
//
//            if(filteredList.isEmpty()){
//                binding.searchRecyclerView.visibility = View.INVISIBLE
//                binding.noData.visibility = View.VISIBLE
//            } else {
//                binding.searchRecyclerView.visibility = View.VISIBLE
//                binding.noData.visibility = View.INVISIBLE
//                productListAdapter.setfilteredList(filteredList)
//            }
//        }
//    }
}