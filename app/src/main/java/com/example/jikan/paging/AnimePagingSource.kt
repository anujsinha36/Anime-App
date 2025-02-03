package com.example.jikan.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jikan.api.AnimeAPI
import com.example.jikan.data.models.TopAnime.Data
import com.example.jikan.data.models.TopAnime.Pagination

class AnimePagingSource(
    private val animeAPI: AnimeAPI) : PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
       return try {
           val currentPage = params.key ?: 1
           val response = animeAPI.getTopAnime(currentPage)

           LoadResult.Page(
               data = response.body()!!.data,
               prevKey = if (currentPage == 1) null else currentPage -1,
               nextKey = if (response.body()!!.pagination.has_next_page) currentPage +1 else null
           )
       }
       catch (e: Exception){
           LoadResult.Error(e)
       }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}