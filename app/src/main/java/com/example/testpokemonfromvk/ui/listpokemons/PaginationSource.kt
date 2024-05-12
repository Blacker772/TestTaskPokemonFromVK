package com.example.testpokemonfromvk.ui.listpokemons

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.example.testpokemonfromvk.data.pokemons.listpokemons.PokemonResult
import com.example.testpokemonfromvk.data.responce.ApiService
import retrofit2.HttpException

class PaginationSource(private val apiService: ApiService): PagingSource<Int, PokemonResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
       return try {
           val currentPage = params.key ?: 1
           val response = apiService.getPokemons(currentPage,20)
           val data = response.body()?.results
           val responseData = mutableListOf<PokemonResult>()
           if (data != null) {
               responseData.addAll(data)
           }

           Page(
               data = responseData,
               prevKey = if (currentPage == 1) null else -1,
               nextKey = currentPage.plus(1))
       }catch (e: Exception){
           Error(e)
       }catch (httpE: HttpException){
           Error(httpE)
       }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? {
        return null
    }
}