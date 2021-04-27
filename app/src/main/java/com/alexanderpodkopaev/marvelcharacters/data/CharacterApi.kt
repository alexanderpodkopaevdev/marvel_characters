package com.alexanderpodkopaev.marvelcharacters.data

import com.alexanderpodkopaev.marvelcharacters.data.model.json.AllCharactersData
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int
    ): AllCharactersData
}
