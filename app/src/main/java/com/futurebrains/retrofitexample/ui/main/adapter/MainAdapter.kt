package com.futurebrains.retrofitexample.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.futurebrains.retrofitexample.data.model.User
import com.futurebrains.retrofitexample.databinding.ItemLayoutBinding

class MainAdapter :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(val item: ItemLayoutBinding) : RecyclerView.ViewHolder(item.root)

//        fun bind(user: User) {
//            item.tvName.text = user.userName
//            item.tvEmail.text = user.userEmail
//            Glide.with(item.ivAvatar.context).load(user.image).into(item.ivAvatar)
//        }

    private val diffcall = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffcall)

    var users: List<User>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
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
//        holder.bind(users[position])

        holder.item.apply {
            val user = users[position]
            tvName.text = user.userName
            tvEmail.text = user.userEmail
            Glide.with(ivAvatar.context).load(user.image).into(ivAvatar)
        }
    }

    override fun getItemCount(): Int = users.size


//    fun addUsers(users: List<User>){
//        this.users.apply {
//            clear()
//            addAll(users)
//        }
//    }
}