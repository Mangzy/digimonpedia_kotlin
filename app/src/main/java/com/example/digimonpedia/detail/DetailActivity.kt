package com.example.digimonpedia.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.digimonpedia.core.data.Resource
import com.example.digimonpedia.core.domain.model.Digimon
import com.example.digimonpedia.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    binding.toolbar.setNavigationOnClickListener { onSupportNavigateUp() }

        val data = intent.getParcelableExtra<Digimon>(EXTRA_DATA)
    supportActionBar?.title = data?.name ?: "Detail"
    val remoteId = data?.remoteId

        // Prefill basic info from list item
        binding.contentDetail.tvName.text = data?.name ?: ""
        binding.contentDetail.tvLevel.text = data?.level?.let { "Level: $it" } ?: ""
        Glide.with(this)
            .load(data?.img)
            .centerCrop()
            .into(binding.contentDetail.ivPoster)

        // Setup favorite button state and behavior
        var currentFavorite = data?.isFavorite == true
        updateFabIcon(currentFavorite)
        binding.fab.setOnClickListener {
            val digimon = data
            if (digimon != null) {
                currentFavorite = !currentFavorite
                viewModel.setFavoriteDigimon(digimon, currentFavorite)
                updateFabIcon(currentFavorite)
            }
        }

        if (remoteId != null) {
            viewModel.getDetail(remoteId).observe(this) { res ->
                when (res) {
                    is Resource.Loading -> binding.contentDetail.progressBar.visibility = android.view.View.VISIBLE
                    is Resource.Success -> {
                        binding.contentDetail.progressBar.visibility = android.view.View.GONE
                        val detail = res.data
                        if (detail != null) {
                            binding.contentDetail.tvName.text = detail.name
                            binding.contentDetail.tvLevel.text = detail.level?.let { "Level: $it" } ?: ""
                            binding.contentDetail.tvDescription.text = detail.description ?: ""
                            val image = detail.image ?: data.img
                            Glide.with(this)
                                .load(image)
                                .centerCrop()
                                .into(binding.contentDetail.ivPoster)
                        }
                    }
                    is Resource.Error -> {
                        binding.contentDetail.progressBar.visibility = android.view.View.GONE
                        // Optionally show a Snackbar or Toast
                    }
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private fun updateFabIcon(isFavorite: Boolean) {
        val icon = if (isFavorite) com.example.digimonpedia.R.drawable.ic_favorite else com.example.digimonpedia.R.drawable.ic_favorite_border
        binding.fab.setImageResource(icon)
    }
}