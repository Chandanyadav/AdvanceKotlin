package com.example.testkotlinapp.presentation


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.UserInfo
import com.example.testkotlinapp.Listener.OnItemClickListener
import com.example.testkotlinapp.R
import com.example.testkotlinapp.common.Constants.Companion.SINGLE_SPACE

class UserAdapter(
    private val context: Context,
    private var users: ArrayList<UserInfo>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    fun updateUsers(newUsers: List<UserInfo>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_user,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        users[position].also { user ->
            user.avatar?.let { imageUrl ->
                Glide.with(context)
                    .load(imageUrl)
                    .into(holder.ivUserCover)
                holder.tvFirstLastName.text = user.firstName + SINGLE_SPACE + user.lastName
                holder.tvEmail.text = user.email
            } ?: kotlin.run {
                Glide.with(context)
                    .load(R.drawable.ic_launcher_background)
                    .into(holder.ivUserCover)
                holder.tvFirstLastName.text = user.firstName + SINGLE_SPACE + user.lastName
                holder.tvEmail.text = user.email
            }
        }
    }


    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFirstLastName: TextView = itemView.findViewById(R.id.tvFirstLastName)
        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        val ivUserCover: ImageView = itemView.findViewById(R.id.ivUserCover)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(users[adapterPosition].id )
            }

        }

    }
}