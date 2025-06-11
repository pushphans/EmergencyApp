package com.example.college.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.college.R
import com.example.college.db.DataClass

class ContactAdapter : ListAdapter<DataClass, ContactAdapter.MyViewHolder>(diffCallback()) {

    private var onClicked : ((DataClass) -> Unit)? = null
    fun setonOnClicked(listener : (DataClass) -> Unit){
        onClicked = listener
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_number, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactAdapter.MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.name.text = item.name
        holder.number.text = item.number

        holder.itemView.setOnClickListener {
            onClicked?.invoke(item)
        }
    }

    inner class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.tvName)
        val number = view.findViewById<TextView>(R.id.tvNumber)
    }


    class diffCallback() : DiffUtil.ItemCallback<DataClass>(){
        override fun areItemsTheSame(oldItem: DataClass, newItem: DataClass): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataClass, newItem: DataClass): Boolean {
            return oldItem == newItem
        }
    }
}