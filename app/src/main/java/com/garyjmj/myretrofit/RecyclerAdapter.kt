package com.garyjmj.myretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerAdapter(private val userList : List<User>, var clickListener: OnUserItemClickListener):
        RecyclerView.Adapter<RecyclerAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: User, action: OnUserItemClickListener) {
            itemView.userId_tV.text = item.userId.toString()
            itemView.id_tV.text = item.id.toString()
            itemView.title_tV.text = item.title
            itemView.body_tV.text = item.body

            itemView.setOnClickListener{
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        holder.bindMovie(movies.get(position))
        val item = userList[position]
        holder.bind(item, clickListener)
    }

    override fun getItemCount(): Int = userList.size
}