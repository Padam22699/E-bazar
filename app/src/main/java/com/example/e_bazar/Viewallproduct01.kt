package com.example.e_bazar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_bazar.Adapters.Viewall01_Adapter
import com.example.e_bazar.inteface.viewall01_ApiInterface
import com.example.e_bazar.viewall_item.viewall01Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Viewallproduct01 : AppCompatActivity() {

    val baseUrl="https://jsonplaceholder.typicode.com/"
    lateinit var recycleview: RecyclerView
    lateinit var myAdapter:Viewall01_Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewallproduct01)

        recycleview=findViewById(R.id.recycleviewV01)
        recycleview.setHasFixedSize(true)
        val linearLayoutManager= GridLayoutManager(this,2)
        recycleview.layoutManager=linearLayoutManager
        getdata()
    }

    private fun getdata() {
        val retrofitBuilder= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create( viewall01_ApiInterface::class.java)

        val retrofitData=retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<ArrayList<viewall01Item>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ArrayList<viewall01Item>?> ,
                response: Response<ArrayList<viewall01Item>?> ,
            ) {
                val responseBody=response.body()!!
                myAdapter= Viewall01_Adapter(baseContext ,responseBody)
                myAdapter.notifyDataSetChanged()
                recycleview.adapter=myAdapter

            }
            override fun onFailure(call: Call<ArrayList<viewall01Item>?> , t: Throwable) {
                Log.d("MainActivity" , "onFailure ${t.message}")
            } })

    }



}
