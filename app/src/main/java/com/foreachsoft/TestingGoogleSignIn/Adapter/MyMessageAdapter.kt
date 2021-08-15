package com.foreachsoft.TestingGoogleSignIn.Adapter

import com.foreachsoft.TestingGoogleSignIn.Model.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.foreachsoft.TestingGoogleSignIn.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class MyMessageAdapter(private var messageList: MutableList<Message>) :
    RecyclerView.Adapter<MyMessageAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.image
        val text: TextView = itemView.txt_name
        val time: TextView = itemView.time


        fun bind(listItem: Message) {
            image.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${listItem.image}", Toast.LENGTH_SHORT)
                    .show()

            }
            itemView.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${listItem.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = messageList[position]
        holder.bind(listItem)

        Picasso.get().load(listItem.image).into(holder.image)
        holder.text.text = listItem.text
        holder.time.text = listItem.time.toString()
    }

    override fun getItemCount() = messageList.size

    fun setMessagesList(list: MutableList<Message>) {
        messageList = list
        notifyDataSetChanged()
    }
}