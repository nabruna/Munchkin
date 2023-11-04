package com.example.munchkin.services.model

class ValidateFields {
    fun verifyEmptyField(playerName: String): Boolean {
        return playerName.isEmpty()
    }

    fun verifyIfPlayerLimit(players: Int): Boolean {
        return players < 10
    }
}