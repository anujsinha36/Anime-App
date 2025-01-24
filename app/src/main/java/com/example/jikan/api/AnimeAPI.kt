package com.example.jikan.api

import com.example.jikan.data.models.AnimeDetails.AnimeDetailsPage
import com.example.jikan.data.models.TopAnime.Data
import com.example.jikan.data.models.TopAnime.TopAnime
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeAPI {

    @GET("top/anime")
    suspend fun getTopAnime(): Response<TopAnime> // response from top anime API endpoint

   @GET("anime/{anime_id}")
   suspend fun getAnimeDetails(
       @Path("anime_id") animeId: Int)
   : Response<AnimeDetailsPage>  //response from anime API endpoint based on anime id

}