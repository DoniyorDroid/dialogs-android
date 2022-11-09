package com.example.dialogs_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dialogs_android.R
import com.example.dialogs_android.databinding.ItemUserBinding
import com.example.dialogs_android.model.User

class UserAdapter(
    private val list: ArrayList<User>,
    val onClick: OnClick
) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(user: User, position: Int) {
            val bind = ItemUserBinding.bind(itemView)
            bind.tv1.text = user.name
            bind.tv2.text = user.number

            bind.root.setOnLongClickListener {
                onClick.onItemLongClickListener(user, position)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnClick {
        fun onItemLongClickListener(user: User, position: Int)
    }
}