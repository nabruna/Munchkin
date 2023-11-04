package com.example.munchkin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.munchkin.services.model.Player
import com.example.munchkin.services.model.ValidateFields
import com.example.munchkin.services.repository.PlayerRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var playersList = MutableLiveData<List<Player>>()
    private var playerRepository = PlayerRepository(application.applicationContext)
    private var txtToast = MutableLiveData<String>()
    private var validation = ValidateFields()
    private var totalPlayers = playerRepository.getTotalPlayers()

    fun getTxtToast() : LiveData<String> {
        return txtToast
    }

    fun getPlayerList() : LiveData<List<Player>> {
        return playersList
    }

    fun getPlayersFromDb(){
        playersList.value = playerRepository.getAllPlayers()
    }

    fun deletePlayer(player: Player) {
        playerRepository.deletePlayer(player)
        txtToast.value = "Player deleted successfully."
        updateTotalPlayers()
    }

    private fun updateTotalPlayers() {
        totalPlayers = playerRepository.getTotalPlayers()
    }

    fun checkPlayerLimit() : Boolean {
        if (!validation.verifyIfPlayerLimit(totalPlayers)){
            txtToast.value = "Cannot add more than 10 players."
            return false
        }
        return true
    }
}