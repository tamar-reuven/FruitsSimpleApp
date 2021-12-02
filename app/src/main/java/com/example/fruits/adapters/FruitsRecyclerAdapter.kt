package com.example.fruits.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fruits.interfaces.OnFruitClickListener
import com.example.fruits.R
import com.example.fruits.models.Fruit


class FruitsRecyclerAdapter(private val context: Context, private val fruits: MutableList<Fruit>, private val listener: OnFruitClickListener) : RecyclerView.Adapter<FruitsRecyclerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fruit_row,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text=fruits[position].name
        Glide.with(context).load(fruits[position].urlToImage).into(holder.itemImage);
    }

    override fun getItemCount(): Int {
        return fruits.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var itemName: TextView = itemView.findViewById(R.id.fruit_name)
        var itemImage: ImageView = itemView.findViewById(R.id.fruit_image)

        init{
           itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onFruitItemClick(position)
            }
        }

    }

}


