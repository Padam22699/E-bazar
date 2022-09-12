package com.example.e_bazar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_bazar.Adapters.GridItemCliked
import com.example.e_bazar.databinding.ActivityGridItemBinding
import com.example.e_bazar.gridview_model.gridItem_clickedItem
import com.example.e_bazar.inteface.gridItem_clicked
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GridItem_Activity : AppCompatActivity() {
    val baseUrl="https://jsonplaceholder.typicode.com/"
    lateinit var  binding:ActivityGridItemBinding
    lateinit var adapter: GridItemCliked

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGridItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val linearLayoutManager=GridLayoutManager(this,2)
        binding.grideItemClickedRecyclerView.layoutManager=linearLayoutManager
         getData()
    }

    private fun getData() {
      val retrofitBuilder =Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl(baseUrl)
          .build()
          .create(gridItem_clicked::class.java)
        val retrofiteData=retrofitBuilder.getData()
        retrofiteData.enqueue(object :Callback<ArrayList<gridItem_clickedItem>?>{
            override fun onResponse(
                call: Call<ArrayList<gridItem_clickedItem>?> ,
                response: Response<ArrayList<gridItem_clickedItem>?> ,
            ) {
              val responseBody=response.body()!!
                adapter=GridItemCliked(baseContext,responseBody)
                adapter.notifyDataSetChanged()
                binding.grideItemClickedRecyclerView.adapter=adapter
            }

            override fun onFailure(call: Call<ArrayList<gridItem_clickedItem>?> , t: Throwable) {
                Log.d("MainActivity" , "onFailure ${t.message}")
            }


        })
    }
}