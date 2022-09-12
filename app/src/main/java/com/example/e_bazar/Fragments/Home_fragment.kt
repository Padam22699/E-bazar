package com.example.e_bazar.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.e_bazar.Adapters.Gridview_Adapter
import com.example.e_bazar.Adapters.recycleview01_Adapter
import com.example.e_bazar.Adapters.recycleview02_Adapter
import com.example.e_bazar.GridItem_Activity
import com.example.e_bazar.R
import com.example.e_bazar.RecycleActivity
import com.example.e_bazar.Viewallproduct01
import com.example.e_bazar.gridview_model.gridview_model
import com.example.e_bazar.recycleview_item.recycleview01_item
import com.example.e_bazar.recycleview_item.recycleview02_item
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.fragment_home_fragment.*


class Home_fragment : Fragment(),recycleview01_Adapter.myOnClikListner {
lateinit var viewall01:Button
 lateinit var gridView:GridView
    var productList=ArrayList<gridview_model>()
    lateinit var gridview2:GridView
     var recycleProduct01=ArrayList<recycleview01_item>()
     var recycleProduct02=ArrayList<recycleview02_item>()

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View? {
         val view = inflater.inflate(R.layout.fragment_home_fragment , container , false)

        viewall01=view.findViewById(R.id.viewall01)
        viewall01.setOnClickListener{
            startActivity(Intent(this.activity,Viewallproduct01::class.java))
        }



        //===============================================================================
        val mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.requireActivity())
        val bundle = Bundle()
        bundle.putInt("card_id" , 912)
        mFirebaseAnalytics.logEvent("card_open" , bundle)

        //================================Image Slide View
         val imageSlider=view.findViewById<ImageSlider>(R.id.imageSlider)
        val imageList=ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.pic4))
        imageList.add(SlideModel(R.drawable.pic3))
        imageList.add(SlideModel(R.drawable.pic2))
        imageList.add(SlideModel(R.drawable.pic1))
        imageSlider.setImageList(imageList, ScaleTypes.FIT)

    //====================Image Gride View===
    gridView=view.findViewById(R.id.gridview)
        productList.add(gridview_model("Best Collection",R.drawable.coloction))
        productList.add(gridview_model("Casual",R.drawable.casual))
        productList.add(gridview_model("Denim",R.drawable.denim))
        productList.add(gridview_model("Jackets", R.drawable.jackest))
        productList.add(gridview_model("Corduroy", R.drawable.cordery))
        productList.add(gridview_model("Shoes", R.drawable.shoes))
        productList.add(gridview_model("Traditial", R.drawable.traditinal))
        productList.add(gridview_model("Accessories for Men", R.drawable.acceseries))

        var productAdapter = Gridview_Adapter(productList,this.activity)
        gridView.adapter=productAdapter


        gridView.setOnItemClickListener(OnItemClickListener { parent , v , position , id ->


            val intent=Intent(requireContext(),GridItem_Activity::class.java)
            startActivity(intent)
        })


    //================ricycleView01

        recycleProduct01.add(recycleview01_item("For Man",302.23,R.drawable.pic3))
        recycleProduct01.add(recycleview01_item("For Man",302.23,R.drawable.pic2))
        recycleProduct01.add(recycleview01_item("For Man",302.23,R.drawable.pic1))
        recycleProduct01.add(recycleview01_item("For Man",302.23,R.drawable.pic4))
        recycleProduct01.add(recycleview01_item("For Man",302.23,R.drawable.pic1))

        val recycleview01=view.findViewById<RecyclerView>(R.id.recycleviewV01)
        recycleview01.layoutManager= LinearLayoutManager(this.activity,LinearLayoutManager.HORIZONTAL,false)
        recycleview01.adapter=recycleview01_Adapter(context,recycleProduct01,this@Home_fragment)
     //=================ricycleview02


        recycleProduct02.add(recycleview02_item("For Man","About_Prodect",R.drawable.pic4))
        recycleProduct02.add(recycleview02_item("For Man","About_Prodect",R.drawable.pic1))
        recycleProduct02.add(recycleview02_item("For Man","About_Prodect",R.drawable.pic2))
        recycleProduct02.add(recycleview02_item("For Man","About_Prodect",R.drawable.pic3))

        val recycleview02=view.findViewById<RecyclerView>(R.id.recycleview02)
        recycleview02.layoutManager=LinearLayoutManager(this.activity,LinearLayoutManager.HORIZONTAL,false)
        recycleview02.adapter=recycleview02_Adapter(context,recycleProduct02)
        return view
    }

    override fun onClick(position: Int) {
     val intent=Intent(requireActivity(), RecycleActivity::class.java)
        intent.putExtra("title",recycleProduct01[position].title)
        intent.putExtra("price",recycleProduct01[position].price)
        intent.putExtra("image",recycleProduct01[position].image)
        startActivity(intent)
    }

}