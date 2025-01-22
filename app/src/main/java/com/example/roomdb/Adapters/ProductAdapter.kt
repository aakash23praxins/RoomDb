package com.example.roomdb.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.Model.Product
import com.example.roomdb.R

class ProductAdapter() :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var dataList: List<Product> = ArrayList()

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productTitleText: TextView = itemView.findViewById(R.id.txtProduct)
        val productPriceText: TextView = itemView.findViewById(R.id.txtProductPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = dataList[position]
        holder.productTitleText.text = data.name
        holder.productPriceText.text = data.price.toString()

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(product: List<Product>) {
        this.dataList = product
        notifyDataSetChanged()
    }
    fun getSpecificData(position: Int):Product{
        return dataList[position]
    }
}