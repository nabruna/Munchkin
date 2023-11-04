package com.example.munchkin.services.repository

import androidx.room.*
import com.example.munchkin.services.model.Player

@Dao
interface PlayerDAO{
    @Insert
    fun savePlayer(player: Player): Long

    @Delete
    fun deletePlayer(player: Player)

    @Update
    fun updatePlayer(player: Player)

    @Query("SELECT * from players WHERE id = :id")
    fun getPlayer(id: Int): Player

    @Query("SELECT * FROM players ORDER BY strength DESC")
    fun getAllPlayers(): List<Player>

    @Query("SELECT count(*) FROM players")
    fun getTotalPlayers(): Int
}