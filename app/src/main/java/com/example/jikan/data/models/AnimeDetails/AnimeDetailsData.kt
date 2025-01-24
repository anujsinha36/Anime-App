package com.example.jikan.data.models.AnimeDetails

import com.example.jikan.data.models.TopAnime.Genre
import com.example.jikan.data.models.TopAnime.Images
import com.example.jikan.data.models.TopAnime.Title
import com.example.jikan.data.models.TopAnime.Trailer

data class AnimeDetailsData(
    val duration: String,
    val episodes: Int,
    val favorites: Int,
    val genres: List<Genre>,
    val images: Images,
    val mal_id: Int,
    val members: Int,
    val popularity: Int,
    val rank: Int,
    val rating: String,
    val score: Double,
    val scored_by: Int,
    val season: String,
    val status: String,
    val synopsis: String,
    val title: String,
    val title_english: String,
    val title_japanese: String,
    val title_synonyms: List<String>,
    val titles: List<Title>,
    val trailer: Trailer,
    val type: String,
    val url: String,
    val year: Int
)
