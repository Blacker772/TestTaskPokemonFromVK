package com.example.testpokemonfromvk.ui.detailspokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testpokemonfromvk.data.pokemons.infopokemons.PokemonDetailsDataModel
import com.example.testpokemonfromvk.data.responce.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val apiService: ApiService
):ViewModel() {

    private val _liveData = MutableLiveData<PokemonDetailsDataModel>()
    val liveData: LiveData<PokemonDetailsDataModel> get() = _liveData

    suspend fun getInfoPokemon(name:String){
        val result = apiService.getImage(name)
        if (result.isSuccessful){
            _liveData.postValue(result.body())
        }
    }
}