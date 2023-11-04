package com.example.munchkin.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.munchkin.R

class PlayerViewHolder(playerLayout: View) : RecyclerView.ViewHolder(playerLayout) {
    val playerNameTextView: TextView = itemView.findViewById(R.id.txtNamePlayer)
    val playerLvlTextView: TextView = itemView.findViewById(R.id.txtLvlPlayer)
    val playerGearTextView: TextView = itemView.findViewById(R.id.txtGearPlayer)
    val playerModTextView: TextView = itemView.findViewById(R.id.txtModPlayer)
    val playerStrTextView: TextView = itemView.findViewById(R.id.txtStrPlayer)
}