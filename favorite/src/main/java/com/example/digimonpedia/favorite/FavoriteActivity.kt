package com.example.digimonpedia.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digimonpedia.core.ui.DigimonAdapter
import com.example.digimonpedia.detail.DetailActivity
import com.example.digimonpedia.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(favoriteModule)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(com.example.digimonpedia.R.string.menu_favorite)

        val adapter = DigimonAdapter()
        adapter.onItemClick = { selected ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selected)
            startActivity(intent)
        }

        binding.rvDigimon.layoutManager = LinearLayoutManager(this)
        binding.rvDigimon.setHasFixedSize(true)
        binding.rvDigimon.adapter = adapter

    binding.progressBar.visibility = android.view.View.GONE
    binding.emptyContainer.visibility = android.view.View.GONE

        viewModel.favorites.observe(this) { list ->
            adapter.submitList(list)
            binding.emptyContainer.visibility = if (list.isNullOrEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}