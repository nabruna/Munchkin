package com.example.munchkin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.munchkin.services.model.Player
import com.example.munchkin.services.model.ValidateFields
import com.example.munchkin.services.repository.PlayerRepository

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private var txtToast = MutableLiveData<String>()
    private var validation = ValidateFields()
    private var playerRepository = PlayerRepository(application.applicationContext)
    private var playerFromDB = MutableLiveData<Player?>()

    fun getPlayerFromDb(): MutableLiveData<Player?> {
        return playerFromDB
    }

    fun getToastTxt(): LiveData<String> {
        return txtToast
    }

    fun getPlayer(id: Int) {
        playerFromDB.value = playerRepository.getPlayer(id)
    }

    fun updatePlayer(player: Player) {
        if (validation.verifyEmptyField(player.name)) {
            txtToast.value = "The player must have a name."
        } else {
            playerRepository.updatePlayer(player)
            txtToast.value = "Player updated."
        }
    }
}