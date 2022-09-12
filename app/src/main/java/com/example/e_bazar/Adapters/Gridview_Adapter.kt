package com.example.e_bazar.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.getSystemService
import com.example.e_bazar.Fragments.Home_fragment
import com.example.e_bazar.R
import com.example.e_bazar.gridview_model.gridview_model

class Gridview_Adapter(
    private val productlist:ArrayList<gridview_model> ,
    private val context: Context?
) : BaseAdapter(){

    private var layoutInflater:LayoutInflater?=null
    private lateinit var title:TextView
    private lateinit var Images:ImageView

    override fun getCount(): Int {
     return productlist.size
    }

    override fun getItem(position: Int): Any? {
       return null
    }

    override fun getItemId(position: Int): Long {
      return 0
    }

    override fun getView(position: Int , convertView: View? , parent: ViewGroup?): View {
     var convertView=convertView

        if(layoutInflater == null){
            layoutInflater=context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        }

        if(convertView == null ){
            convertView=layoutInflater!!.inflate(R.layout.gridview_item,null)
        }
         title=convertView!!.findViewById(R.id.gride_title)
        Images=convertView!!.findViewById(R.id.Grid_image)

        Images.setImageResource(productlist.get(position).Imges)
        title.setText(productlist.get(position).title)
      return convertView
    }


}