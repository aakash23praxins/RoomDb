package com.example.roomdb.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.Model.Product
import com.example.roomdb.R
import com.example.roomdb.View.MainActivity
import com.example.roomdb.View.UpdateDataActivity

class ProductAdapter(private val activity: MainActivity) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var dataList: List<Product> = ArrayList()

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productTitleText: TextView = itemView.findViewById(R.id.txtProduct)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
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

        holder.cardView.setOnClickListener {
            val intent = Intent(activity, UpdateDataActivity::class.java)
                .apply {
                    this.putExtra("id", data.id)
                        .putExtra("name", data.name)
                        .putExtra("price", data.price)
                }
            activity.updateActivityResult.launch(intent)
        }
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