package com.futurebrains.retrofitexample.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.futurebrains.retrofitexample.data.model.User
import com.futurebrains.retrofitexample.databinding.ItemLayoutBinding

class MainAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    class DataViewHolder(private val item: ItemLayoutBinding) : RecyclerView.ViewHolder(item.root) {

        fun bind(user: User) {
            item.tvName.text = user.userName
            item.tvEmail.text = user.userEmail
            Glide.with(item.ivAvatar.context).load(user.image).into(item.ivAvatar)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
     return users.size
    }

    fun addUsers(users: List<User>){
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}