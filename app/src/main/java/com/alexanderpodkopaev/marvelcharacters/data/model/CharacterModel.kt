package com.alexanderpodkopaev.marvelcharacters.data.model

import com.alexanderpodkopaev.marvelcharacters.data.model.json.CharacterJsonModel
import java.io.Serializable

data class CharacterModel(val name: String, val bio: String, val image: String) : Serializable


fun CharacterJsonModel.toModel(): CharacterModel = CharacterModel(
    name = this.name,
    bio = if (this.description.isNotEmpty()) this.description.trim() else "Bio not found",
    image = this.thumbnail.path + "." + this.thumbnail.extension
)