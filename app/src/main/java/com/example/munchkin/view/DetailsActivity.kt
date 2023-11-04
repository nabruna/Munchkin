package com.example.munchkin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.munchkin.databinding.ActivityDetailsBinding
import com.example.munchkin.services.model.Player
import com.example.munchkin.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var playerFromDB: Player
    private var idPlayer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        setObservers()

        idPlayer = intent.getIntExtra("id", idPlayer)

        if (idPlayer > 0) {
            detailsViewModel.getPlayer(idPlayer)
        }

        binding.btnAddLvl.setOnClickListener {
            playerFromDB.level++
            updatePlayerStats()
        }

        binding.btnSubLvl.setOnClickListener {
            if (playerFromDB.level - 1 >= 0) {
                playerFromDB.level--
                updatePlayerStats()
            } else {
                Toast.makeText(this, "Level cannot be negative.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnAddGear.setOnClickListener {
            playerFromDB.gear++
            updatePlayerStats()
        }

        binding.btnSubGear.setOnClickListener {
            if (playerFromDB.gear - 1 >= 0) {
                playerFromDB.gear--
                updatePlayerStats()
            } else {
                Toast.makeText(this, "Gear cannot be negative.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnAddMod.setOnClickListener {
            playerFromDB.modifier++
            updatePlayerStats()
        }

        binding.btnSubMod.setOnClickListener {
            playerFromDB.modifier--
            updatePlayerStats()
        }

        binding.btnSavePlayer.setOnClickListener {
            val playerName = binding.txtNamePlayer.text.toString()
            val playerLvl = binding.totalLvl.text.toString().toInt()
            val playerGear = binding.totalGear.text.toString().toInt()
            val playerMod = binding.totalMod.text.toString().toInt()

            val player = Player(idPlayer, playerName, playerLvl, playerGear, playerMod)

            if (idPlayer > 0) {
                if (player != playerFromDB) {
                    detailsViewModel.updatePlayer(player)
                }
                finish()
            }
        }
    }

    private fun setObservers() {
        detailsViewModel.getToastTxt().observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        detailsViewModel.getPlayerFromDb().observe(this) {
            if (it != null) {
                playerFromDB = it
            }
            updatePlayerStats()
        }
    }

    private fun updatePlayerStats(){
        binding.txtNamePlayer.setText(playerFromDB.name)
        binding.totalMod.text = playerFromDB.modifier.toString()
        binding.totalGear.text = playerFromDB.gear.toString()
        binding.totalLvl.text = playerFromDB.level.toString()
        binding.totalStr.text = (playerFromDB.level + playerFromDB.gear + playerFromDB.modifier).toString()
    }
}
