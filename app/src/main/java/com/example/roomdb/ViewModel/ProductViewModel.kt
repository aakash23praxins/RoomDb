package com.example.roomdb.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.roomdb.Model.Product
import com.example.roomdb.Repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    val getAllData: LiveData<List<Product>> = repository.getAllProduct

    fun addData(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(product)
    }


    fun updateData(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(product)
    }

    fun deleteData(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(product)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}

