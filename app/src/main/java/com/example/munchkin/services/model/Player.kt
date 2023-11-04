package com.example.munchkin.services.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player(
    @ColumnInfo @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo var name: String,
    @ColumnInfo var level: Int,
    @ColumnInfo var gear: Int,
    @ColumnInfo var modifier: Int,
    @ColumnInfo var strength: Int = gear + level + modifier
)