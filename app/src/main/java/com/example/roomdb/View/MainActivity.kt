package com.example.roomdb.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.Adapters.ProductAdapter
import com.example.roomdb.Model.Product
import com.example.roomdb.ProductApplication
import com.example.roomdb.R
import com.example.roomdb.ViewModel.ProductViewModel
import com.example.roomdb.ViewModel.ProductViewModelFactory
import com.example.roomdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var productViewModel: ProductViewModel

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var updateActivityResult: ActivityResultLauncher<Intent>

    lateinit var binding: ActivityMainBinding
    lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        productAdapter = ProductAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = productAdapter

        registerActivityLauncher()

        val view = binding.root
        setContentView(view)
        val viewModelFactory =
            ProductViewModelFactory((application as ProductApplication).repository)

        productViewModel = ViewModelProvider(this,viewModelFactory).get(ProductViewModel::class.java)
        Log.d("Data", "DONE WORK SUCCESSFULLY")
        productViewModel.getAllData.observe(this,  { data->
            productAdapter.setData(data)
        })


        // Add Data
        binding.addBtn.setOnClickListener {
            startAddDataActivity()
        }

        //Delete All Data
        binding.deleteBtn.setOnClickListener {
            deleteAllData()
        }

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                productViewModel.deleteData(productAdapter.getSpecificData(viewHolder.adapterPosition))
            }
        }).attachToRecyclerView(binding.recyclerView)
    }

    private fun deleteAllData() {
        AlertDialog.Builder(this).also {
            it.setTitle("Delete All Data!!")
            it.setMessage("Are you want to delete all data??  If you want delete specific data to swipe left or right of data it will delete..")
            it.setIcon(R.drawable.ic_delete)
            it.setNegativeButton(
                "No"
            ) { p0, p1 ->
                p0.cancel()
            }
            it.setPositiveButton("Yes", { dialog, position ->
                productViewModel.deleteAll()
            }).show()
        }
    }

    private fun registerActivityLauncher() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult(), { data ->
                val resultCode = data.resultCode
                val rawData = data.data
                if (data != null && resultCode == RESULT_OK) {
                    val productName = rawData!!.getStringExtra("product").toString()
                    val price = rawData.getFloatExtra("price", 0.0f)

                    val product = Product(0, productName, price)
                    productViewModel.addData(product)
                    Toast.makeText(this, "Data has been saved!!", Toast.LENGTH_SHORT).show()
                }
            })

        updateActivityResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult(), { data ->
                val resultCode = data.resultCode
                val rawData = data.data

                if (resultCode == 200 && rawData != null) {
                    val productId = rawData.getIntExtra("id", -1)
                    val productName = rawData.getStringExtra("name")
                    val productPrice = rawData.getFloatExtra("price", 0.0f)
                    Log.d("DAATA", "THE DATA $productId, $productName , $productPrice")
                    val product = Product(productId, productName.toString(), productPrice)
                    productViewModel.updateData(product)
//                    Toast.makeText(this, "Data has been Updated.. ", Toast.LENGTH_SHORT).show()
                }

            })
    }


    private fun startAddDataActivity() {
        val intent = Intent(this, AddDataActivity::class.java)
//        startActivity(intent)
        activityResultLauncher.launch(intent)
    }

}