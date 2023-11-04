package com.example.munchkin.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.munchkin.R
import com.example.munchkin.services.model.Player
import com.example.munchkin.view.viewholder.PlayerViewHolder

class PlayerAdapter(private var context: Context) : RecyclerView.Adapter<PlayerViewHolder>() {
    lateinit var playersList : List<Player>
    var onItemLongClick : ((Int) -> Unit)? = null
    var onItemClick : ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val playerLayout = LayoutInflater.from(context)
            .inflate(R.layout.player_layout, parent, false)
        return PlayerViewHolder(playerLayout)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val playerData = playersList[position]

        holder.playerNameTextView.text = playerData.name
        holder.playerLvlTextView.text = "${playerData.level}"
        holder.playerGearTextView.text = "${playerData.gear}"
        holder.playerModTextView.text = "${playerData.modifier}"
        holder.playerStrTextView.text = "${playerData.strength}"

        // delete player
        holder.itemView.setOnLongClickListener{
            onItemLongClick?.invoke(position)
            true
        }

        // edit player
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(position)
        }

        val context = holder.itemView.context
        val backgroundColor = if (playerData.strength >= 15) {
            context.resources.getColor(R.color.green_200, context.theme)
        } else {
            Color.WHITE
        }
        holder.itemView.setBackgroundColor(backgroundColor)
    }

    override fun getItemCount(): Int {
        return playersList.size
    }
}