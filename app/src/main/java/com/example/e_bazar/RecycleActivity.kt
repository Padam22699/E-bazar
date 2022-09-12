package com.example.e_bazar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_bazar.R
import com.example.e_bazar.databinding.ActivityRecycleBinding

class RecycleActivity : AppCompatActivity() {
    lateinit var binding:ActivityRecycleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

          val title=intent.getStringExtra("title")

          val price=intent.getDoubleExtra("price",00.0)
           var bundle: Bundle? =intent.extras
          if(bundle!=null){
              var image:Int=bundle.getInt("image")
              binding.imageView5.setImageResource(image)
          }
        binding.price.text=price.toString()
        binding.title01.text=title
        binding.textView12.text=" $ ${price.toString()}"


    }
}