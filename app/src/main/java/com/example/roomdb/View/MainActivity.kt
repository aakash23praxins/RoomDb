package com.example.roomdb.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdb.ProductApplication
import com.example.roomdb.ViewModel.ProductViewModel
import com.example.roomdb.ViewModel.ProductViewModelFactory
import com.example.roomdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var productViewModel: ProductViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        val viewModelFactory =
            ProductViewModelFactory((application as ProductApplication).repository)

        productViewModel = ViewModelProvider(this,viewModelFactory).get(ProductViewModel::class.java)

        productViewModel.getAllData.observe(this,  { data->

        })
    }
}