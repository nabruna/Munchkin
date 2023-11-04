package com.example.munchkin.services.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.munchkin.services.model.Player

@Database(entities = [Player::class], version = 1)
abstract class PlayerDatabase : RoomDatabase() {
    abstract fun getDAO() : PlayerDAO

    companion object{
        private lateinit var INSTANCE : PlayerDatabase

        fun getInstance(context: Context): PlayerDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, PlayerDatabase::class.java, "players_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}