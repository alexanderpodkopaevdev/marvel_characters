package com.alexanderpodkopaev.marvelcharacters.data.model.json

import kotlinx.serialization.Serializable

@Serializable
data class CharacterJsonModel(
    val description: String,
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail,
)