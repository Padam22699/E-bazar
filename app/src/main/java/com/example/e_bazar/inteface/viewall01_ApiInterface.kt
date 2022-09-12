package com.example.e_bazar.inteface

import com.example.e_bazar.viewall_item.viewall01Item
import retrofit2.Call
import retrofit2.http.GET

interface viewall01_ApiInterface {
    @GET("photos")
    fun getData(): Call<ArrayList<viewall01Item>>
}