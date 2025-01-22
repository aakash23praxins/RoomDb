package com.example.roomdb.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdb.databinding.ActivityUpdateDataBinding

class UpdateDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAndSetData()
        binding.btnCancel.setOnClickListener {
            finish()
        }
        binding.btnUpdate.setOnClickListener {
            val productName = binding.edtUpdProduct.text.toString()
            val productPrice = binding.edtUpdPrice.text.toString()
            if (productName.isNotEmpty() && productPrice.isNotEmpty()) {
                val id = intent.getIntExtra("id", -1)

                Log.d("updatedDATA ", "THD $productName , $productPrice $id")
                updateProductData(id, productName, productPrice.toFloat())
            }
        }
    }

    private fun getAndSetData() {
        val name = intent.getStringExtra("name")
        val price = intent.getFloatExtra("price", 0.0f)

        binding.edtUpdPrice.setText(price.toString())
        binding.edtUpdProduct.setText(name)
    }

    private fun updateProductData(id: Int, name: String, price: Float) {
        val intent = Intent()
        intent.putExtra("id", id)
        intent.putExtra("name", name)
        intent.putExtra("price", price)


        setResult(200, intent)
        finish()
    }
}