package com.example.testpokemonfromvk.data.pokemons.infopokemons

import com.squareup.moshi.JsonClass
import javax.inject.Inject

data class PokemonDetailsDataModel @Inject constructor(
    val name:String,
    val sprites: Sprites
)
