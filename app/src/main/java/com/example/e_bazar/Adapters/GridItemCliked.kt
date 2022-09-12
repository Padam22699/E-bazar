package com.example.e_bazar.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_bazar.R
import com.example.e_bazar.gridview_model.gridItem_clickedItem
import com.example.e_bazar.viewall_item.viewall01Item

class GridItemCliked (val context: Context , val prodectListV01:ArrayList<gridItem_clickedItem> , ):RecyclerView.Adapter<GridItemCliked.ViewHolder>() {
    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val titele=ItemView.findViewById<TextView>(R.id.titleR01)
        val price=ItemView.findViewById<TextView>(R.id.priceR01)
        val image=ItemView.findViewById<ImageView>(R.id.imageR01)

    }
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.grideitem_clicked,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        val imgURL:String=prodectListV01[position].url
        Glide.with(context).load(imgURL).centerCrop().into(holder.image)
        holder.titele.text=prodectListV01[position].title
        holder.price.text= prodectListV01[position].thumbnailUrl.toString()
    }

    override fun getItemCount(): Int {
        return prodectListV01.size
    }


}