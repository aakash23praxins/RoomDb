package com.example.roomdb.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.roomdb.Model.Product
import com.example.roomdb.Room.ProductDao
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {
    val getAllProduct: LiveData<List<Product>> = productDao.fetchAllProduct()

    @WorkerThread
    suspend fun insert(product: Product) {
        productDao.addProduct(product)
    }

    @WorkerThread
    suspend fun update(product: Product) {
        productDao.updateProduct(product)
    }

    @WorkerThread
    suspend fun delete(product: Product) {
        productDao.deleteProduct(product)
    }

    fun deleteAll() {
        productDao.deleteAllProduct()
    }
}