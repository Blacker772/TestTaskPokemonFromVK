package com.example.testpokemonfromvk.data.pokemons.listpokemons

import com.squareup.moshi.JsonClass
import javax.inject.Inject

data class PokemonResult @Inject constructor(
    val name: String?,
    val url: String?
)
