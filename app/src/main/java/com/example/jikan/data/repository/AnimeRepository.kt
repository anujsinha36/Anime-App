package com.example.jikan.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.jikan.api.AnimeAPI
import com.example.jikan.data.models.AnimeDetails.AnimeDetailsPage
import com.example.jikan.data.models.TopAnime.Data
import com.example.jikan.data.models.TopAnime.TopAnime
import com.example.jikan.paging.AnimePagingSource
import retrofit2.Response

class AnimeRepository(
    private val animeAPI: AnimeAPI
) {
    fun getPagedAnimeList() = Pager(
        config = PagingConfig(pageSize = 25, maxSize = 100),
        pagingSourceFactory = {AnimePagingSource(animeAPI)}
    ).liveData

    //get anime details
    suspend fun getAnimeDetails(animeId: Int) = animeAPI.getAnimeDetails(animeId)

}