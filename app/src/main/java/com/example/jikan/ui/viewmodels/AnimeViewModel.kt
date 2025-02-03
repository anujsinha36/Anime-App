package com.example.jikan.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.jikan.data.models.AnimeDetails.AnimeDetailsPage
import com.example.jikan.data.models.TopAnime.TopAnime
import com.example.jikan.data.repository.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeViewModel(
    private val animeRepository: AnimeRepository
) : ViewModel() {
    val animeDetailsLiveData = MutableLiveData<AnimeDetailsPage>()
    val animePagingLiveData = animeRepository.getPagedAnimeList().cachedIn(viewModelScope)


    // function to get anime details from repository
    fun getAnimeDetails(animeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response1 = animeRepository.getAnimeDetails(animeId)
            if (response1.isSuccessful) {
                animeDetailsLiveData.postValue(response1.body())
            }
        }
    }
}