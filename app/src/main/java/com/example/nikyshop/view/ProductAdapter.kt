package com.example.nikyshop.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nikyshop.R
import com.example.nikyshop.model.entity.Product
import com.squareup.picasso.Picasso
import java.util.ArrayList

//get callback and is admin from initialized page for check is admin or no
class ProductAdapter( var itemsCallBack: MainItemsCallBack,var isAdmin: Boolean) :

    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    var list:ArrayList<Product> = ArrayList()

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //initialize items product
        val image: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val tvPrice: TextView = itemView.findViewById(R.id.textViewPrice)
        val tvTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val tvCategory: TextView = itemView.findViewById(R.id.textViewCategory)

        fun onBind(product: Product) {

            //set product image
            Picasso.with(itemView.context)
                .load(product.imageUrl)
                .centerCrop().fit()
                .error(R.drawable.ic_nike_logo)
                .placeholder(R.drawable.ic_nike_logo)
                .into(image)

            //set product texts
            tvTitle.text = product.name
            tvCategory.text = product.category
            tvPrice.text = product.price

            //set on long click on items
            itemView.setOnLongClickListener {
                //check is admin or no for delete product
                if (isAdmin) {
                    itemsCallBack.onLongClick(product)
                }
                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rec_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(list.get(position))
    }

    override fun getItemCount(): Int {
        //list count items
        if (list.isNotEmpty())
            return list.size
        else return 0
    }

    fun setProducts(productList: ArrayList<Product>){
        list=ArrayList()
        list=productList
        notifyDataSetChanged()
    }

    fun deleteProduct(product: Product){
        list.remove(product)
        notifyDataSetChanged()
    }

    fun addProduct(product: Product){
        notifyItemInserted(0)
    }

    interface MainItemsCallBack {
        fun onLongClick(product: Product)
    }
}