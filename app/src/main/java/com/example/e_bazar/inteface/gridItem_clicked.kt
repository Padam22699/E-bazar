package com.example.e_bazar.inteface

import com.example.e_bazar.gridview_model.gridItem_clickedItem
import retrofit2.Call
import retrofit2.http.GET

interface gridItem_clicked {
    @GET("photos")
    fun getData(): Call<ArrayList<gridItem_clickedItem>>
}