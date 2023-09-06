package dev.redfox.productlistlokal.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.redfox.productlistlokal.R
import dev.redfox.productlistlokal.data.models.Product

class ProductListAdapter(
    var productList: MutableList<Product>
) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    var onItemClick: ((Product) -> Unit)? = null

    class ProductViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val productImg: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val brandName: TextView = itemView.findViewById(R.id.brand_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.data_item_layout, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.productName.text = "Title: ${product.title}"
        holder.productPrice.text = "Price: $${product.price}"
        holder.brandName.text = "Brand: ${product.brand}"
        Picasso.get().load(product.thumbnail).into(holder.productImg)

        holder.itemView.setOnClickListener() {
            onItemClick?.invoke(product)
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }


    fun setfilteredList(productList: ArrayList<Product>){
        this.productList = productList
        notifyDataSetChanged()
    }
}