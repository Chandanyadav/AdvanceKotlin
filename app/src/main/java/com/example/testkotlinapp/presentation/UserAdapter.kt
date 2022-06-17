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
import com.example.testkotlinapp.R
import com.example.testkotlinapp.common.Constants.Companion.SINGLE_SPACE

class UserAdapter(
    private val context: Context,
    private var users: ArrayList<UserInfo>
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    fun updateUsers(newUsers: List<UserInfo>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFirstLastName: TextView = view.findViewById(R.id.tvFirstLastName)
        val tvEmail: TextView = view.findViewById(R.id.tvEmail)
        val ivUserCover: ImageView = view.findViewById(R.id.ivUserCover)

    }
}