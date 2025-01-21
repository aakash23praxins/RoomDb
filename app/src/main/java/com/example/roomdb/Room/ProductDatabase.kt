package com.example.roomdb.Room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        var INSTANCE: ProductDatabase? = null

        fun getDatabaseInstance(context: Context): ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}