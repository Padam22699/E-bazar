package com.example.e_bazar.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.e_bazar.R
import kotlinx.android.synthetic.main.fragment_home_fragment.*


class Orderd_Fragment : Fragment() {
    lateinit var imageSlider: ImageSlider

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View? {

         val view = inflater.inflate(R.layout.fragment_orderd_ , container , false)



        return view
    }


    }
