package com.example.testpokemonfromvk.data.pokemons.listpokemons

import com.squareup.moshi.JsonClass
import javax.inject.Inject

data class ListPokemonsDataModel @Inject constructor(
    val results: List<PokemonResult>
)
