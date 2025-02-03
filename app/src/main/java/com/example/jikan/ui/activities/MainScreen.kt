package com.example.jikan.ui.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jikan.R
import com.example.jikan.adapters.AnimeListAdapter
import com.example.jikan.api.RetrofitBuilder
import com.example.jikan.data.repository.AnimeRepository
import com.example.jikan.databinding.ActivityMainBinding
import com.example.jikan.ui.viewmodels.AnimeViewModel
import com.example.jikan.ui.viewmodels.AnimeViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainScreen : AppCompatActivity() {
    private lateinit var viewModel: AnimeViewModel
    private lateinit var repository: AnimeRepository
    private lateinit var viewModelFactory: AnimeViewModelFactory
    private lateinit var binding: ActivityMainBinding
    private lateinit var animeListAdapter: AnimeListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = AnimeRepository(RetrofitBuilder.getRetrofitInstance())
        viewModelFactory = AnimeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AnimeViewModel::class.java]

        animeListAdapter = AnimeListAdapter()
        binding.rvAnimeList.layoutManager = LinearLayoutManager(this)
        binding.rvAnimeList.adapter = animeListAdapter

        //Observe data from view model
        viewModel.animePagingLiveData.observe(this) {
            animeListAdapter.submitData(lifecycle, it)
        }

        // Handle Load State (Show ProgressBar)
        lifecycleScope.launch {
            animeListAdapter.loadStateFlow.collectLatest { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is LoadState.NotLoading -> binding.progressBar.visibility = View.GONE
                    is LoadState.Error -> binding.progressBar.visibility = View.GONE
                }
            }
        }

    }
}