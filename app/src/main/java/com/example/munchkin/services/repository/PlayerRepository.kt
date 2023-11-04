package com.example.munchkin.services.repository

import android.content.Context
import com.example.munchkin.services.model.Player

class PlayerRepository(var context: Context) {
    private val dao = PlayerDatabase.getInstance(context).getDAO()

    fun savePlayer(player: Player) : Boolean {
        return dao.savePlayer(player) > 0
    }

    fun deletePlayer(player: Player) {
        dao.deletePlayer(player)
    }

    fun updatePlayer(player: Player) {
        dao.updatePlayer(player)
    }

    fun getPlayer(id: Int) : Player {
        return dao.getPlayer(id)
    }

    fun getAllPlayers() : List<Player> {
        return dao.getAllPlayers()
    }

    fun getTotalPlayers() : Int {
        return dao.getTotalPlayers()
    }
}