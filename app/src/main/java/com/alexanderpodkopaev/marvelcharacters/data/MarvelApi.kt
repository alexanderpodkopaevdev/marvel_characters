package com.alexanderpodkopaev.marvelcharacters.data

import com.alexanderpodkopaev.marvelcharacters.data.model.json.AllCharactersData
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): AllCharactersData
}
