package com.example.roomdb.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdb.databinding.ActivityAddDataBinding

class AddDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancel.setOnClickListener {
            finish()
        }
        binding.btnAdd.setOnClickListener {
            val productData = binding.edtGetProduct.text.toString()
            val productPrice = binding.edtGetPrice.text.toString()

            if (productData.isNotEmpty() && productPrice.isNotEmpty()) {
                setData(productData, productPrice.toFloat())
            } else {
                Toast.makeText(applicationContext, "Please fill data first!!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setData(productData: String, productPrice: Float) {
        val intent = Intent()
        intent.putExtra("product", productData)
        intent.putExtra("price", productPrice)

        setResult(RESULT_OK, intent)
        finish()
    }
}