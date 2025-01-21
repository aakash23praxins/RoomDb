package com.example.roomdb

import android.app.Application
import com.example.roomdb.Repository.ProductRepository
import com.example.roomdb.Room.ProductDatabase

class ProductApplication : Application() {

    val database by lazy {
        ProductDatabase.getDatabaseInstance(this)
    }

    val repository by lazy {
        ProductRepository(database.productDao())
    }
}