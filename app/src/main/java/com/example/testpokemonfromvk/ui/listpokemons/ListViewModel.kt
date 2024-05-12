package com.example.testpokemonfromvk.ui.listpokemons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.testpokemonfromvk.data.pokemons.infopokemons.PokemonDetailsDataModel
import com.example.testpokemonfromvk.data.responce.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {

    val pokemonList = Pager(PagingConfig(1)){
        PaginationSource(apiService)
    }.flow.cachedIn(viewModelScope)
}
