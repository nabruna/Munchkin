package com.example.munchkin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.munchkin.services.model.Player
import com.example.munchkin.services.model.ValidateFields
import com.example.munchkin.services.repository.PlayerRepository

class AddPlayerViewModel(application: Application) : AndroidViewModel(application) {
    private var txtToast = MutableLiveData<String>()
    private var validateFields = ValidateFields()
    private var playerRepository = PlayerRepository(application.applicationContext)
    private var totalPlayers = playerRepository.getTotalPlayers()

    fun getToastTxt(): LiveData<String>{
        return txtToast
    }

    private fun updateTotalPlayers() {
        totalPlayers = playerRepository.getTotalPlayers()
    }

    fun savePlayer(namePlayer: String) : Boolean {
        val player = Player(0, namePlayer, 0, 0, 0)
0
        if (validateFields.verifyEmptyField(namePlayer)){
            txtToast.value = "Player name must not be empty."
            return false
        }

        if (!playerRepository.savePlayer(player)){
            txtToast.value = "Could not save player. Try again later."
            return false
        }

        txtToast.value = "Player added successfully."
        updateTotalPlayers()
        return true
    }

}