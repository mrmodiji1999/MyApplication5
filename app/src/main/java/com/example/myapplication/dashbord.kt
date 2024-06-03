package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ProductModel.DataShow
import com.example.myapplication.ProductModel.DataShowItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductPage : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbord)

        recyclerView = findViewById(R.id.recyclerView)

        val retrofitData = RetrofitInstance.api.getProducts()



        retrofitData.enqueue(object : Callback<DataShow?> {

            override fun onResponse(call: Call<DataShow?>, response: Response<DataShow?>) {
                // if api call is a success, then use the data of API and show in your app
                var responseBody = response.body()
                val productList = responseBody?.data

                myAdapter = productList?.let { MyAdapter(this@ProductPage, it) }!!
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@ProductPage)
            }

            override fun onFailure(call: Call<DataShowItem?>, t: Throwable) {
                // if api call fails
                Log.d("Main Activity ", "onFailure: " + t.message)
            }
        })



    }
}