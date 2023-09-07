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

class ProductImagesAdapter(
    var imageList: List<String>
) : RecyclerView.Adapter<ProductImagesAdapter.ProductImagesViewHolder>() {


    class ProductImagesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val productImg: ImageView = itemView.findViewById(R.id.ivProductImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImagesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_image_layout, parent, false)
        return ProductImagesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductImagesViewHolder, position: Int) {
        val image = imageList[position]

        Picasso.get().load(image).into(holder.productImg)

    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}