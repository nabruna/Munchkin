package com.example.munchkin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.munchkin.databinding.ActivityMainBinding
import com.example.munchkin.view.adapter.PlayerAdapter
import com.example.munchkin.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PlayerAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PlayerAdapter(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setAdapter()
        setObservers()

        binding.btnNewPlayer.setOnClickListener {
            if (viewModel.checkPlayerLimit()) {
                startActivity(Intent(this, AddPlayerActivity::class.java))
            }
        }
    }

    private fun setAdapter() {
        binding.rcvPlayers.layoutManager = LinearLayoutManager(this)
        binding.rcvPlayers.adapter = adapter

        adapter.onItemLongClick = { position ->
            val playerToDelete = adapter.playersList[position]
            viewModel.deletePlayer(playerToDelete)
            viewModel.getPlayersFromDb()
        }

        adapter.onItemClick = { position ->
            val player = adapter.playersList[position]
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("id", player.id)
            startActivity(intent)
        }
    }

    private fun setObservers() {
        viewModel.getPlayerList().observe(this) { players ->
            adapter.playersList = players // Update the data source
            adapter.notifyDataSetChanged() // Refresh the RecyclerView
        }

        viewModel.getTxtToast().observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPlayersFromDb()
    }
}