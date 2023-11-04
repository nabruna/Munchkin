package com.example.munchkin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.munchkin.databinding.ActivityAddPlayerBinding
import com.example.munchkin.viewmodel.AddPlayerViewModel


class AddPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPlayerBinding
    private lateinit var addPlayerViewModel: AddPlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addPlayerViewModel = ViewModelProvider(this).get(AddPlayerViewModel::class.java)

        setObservers()

        binding.btnNewPlayer.setOnClickListener {
            val playerName = binding.edtPlayerName.text.toString()
            if(addPlayerViewModel.savePlayer(playerName)){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setObservers(){
        addPlayerViewModel.getToastTxt().observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}