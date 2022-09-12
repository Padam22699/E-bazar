package com.example.e_bazar.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_bazar.R
import com.example.e_bazar.recycleview_item.recycleview02_item

class recycleview02_Adapter(val context: Context? , private val productlist:ArrayList<recycleview02_item>):RecyclerView.Adapter<recycleview02_Adapter.ViewHolder>() {
    class ViewHolder(ItemView:View):RecyclerView.ViewHolder(ItemView){
        val title=ItemView.findViewById<TextView>(R.id.titleR02)
        val description=ItemView.findViewById<TextView>(R.id.descriptionR02)
        val image=ItemView.findViewById<ImageView>(R.id.imageR02)
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
      val view=LayoutInflater.from(parent.context)
          .inflate(R.layout.recycleview02_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
      val productitem=productlist[position]
          holder.image.setImageResource(productitem.image)
          holder.title.setText(productitem.title)
          holder.description.setText((productitem.decription))
    }

    override fun getItemCount(): Int {
     return productlist.size
    }

}