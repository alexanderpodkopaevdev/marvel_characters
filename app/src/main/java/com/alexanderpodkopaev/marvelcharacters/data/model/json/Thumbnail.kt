package com.alexanderpodkopaev.marvelcharacters.data.model.json

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    val extension: String,
    val path: String
)