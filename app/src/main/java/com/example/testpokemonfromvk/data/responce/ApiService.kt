package com.example.testpokemonfromvk.data.responce

import com.example.testpokemonfromvk.data.pokemons.listpokemons.ListPokemonsDataModel
import com.example.testpokemonfromvk.data.pokemons.infopokemons.PokemonDetailsDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/v2/pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<ListPokemonsDataModel>

    @GET("api/v2/pokemon/{name}")
    suspend fun getImage(
        @Path("name") name: String
    ): Response<PokemonDetailsDataModel>
}
