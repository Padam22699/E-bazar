package com.example.e_bazar.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.example.e_bazar.R
import com.example.e_bazar.recycleview_item.recycleview01_item

class recycleview01_Adapter(val context: Context?
, private val productlistR01:ArrayList<recycleview01_item>,
val listner:myOnClikListner
):RecyclerView.Adapter<recycleview01_Adapter.ViewHolder>()  {

 inner class ViewHolder (ItemViwe:View):RecyclerView.ViewHolder(ItemViwe){

         val titele=ItemViwe.findViewById<TextView>(R.id.titleR01)
        val price=ItemViwe.findViewById<TextView>(R.id.priceR01)
        val image=ItemViwe.findViewById<ImageView>(R.id.imageR01)
            init {
                itemView.setOnClickListener{
                    val position = adapterPosition
                    listner.onClick(position)
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
           val view=LayoutInflater.from(parent.context)
               .inflate(R.layout.recycleview01_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        val recycleitem=productlistR01[position]
        holder.image.setImageResource(recycleitem.image)
        holder.titele.text=productlistR01[position].title
        holder.price.text= productlistR01[position].price.toString()
    }

    override fun getItemCount(): Int {
        return productlistR01.size
    }

 interface myOnClikListner{
     fun onClick(position: Int)
 }

}
