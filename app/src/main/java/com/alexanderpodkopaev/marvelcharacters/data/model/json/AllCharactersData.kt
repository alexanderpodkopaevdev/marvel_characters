package com.alexanderpodkopaev.marvelcharacters.data.model.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllCharactersData(
    val `data`: Data,
)

@Serializable
data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    @SerialName("results")
    val characterJsonModels: List<CharacterJsonModel>,
    val total: Int
)