package com.example.jikan.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jikan.data.repository.AnimeRepository

class AnimeViewModelFactory(

private val animeRepository: AnimeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeViewModel(animeRepository) as T
    }
}