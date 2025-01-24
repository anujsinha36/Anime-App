package com.example.jikan.data.repository

import android.util.Log
import com.example.jikan.api.AnimeAPI
import com.example.jikan.data.models.AnimeDetails.AnimeDetailsPage
import com.example.jikan.data.models.TopAnime.Data
import com.example.jikan.data.models.TopAnime.TopAnime
import retrofit2.Response

class AnimeRepository(
    private val animeAPI: AnimeAPI
) {
    //function to get top anime list
    suspend fun getAnimeList() = animeAPI.getTopAnime()

    //function to get anime details from AnimeDetailsPage data class
    suspend fun getAnimeDetails(animeId: Int) = animeAPI.getAnimeDetails(animeId)

}