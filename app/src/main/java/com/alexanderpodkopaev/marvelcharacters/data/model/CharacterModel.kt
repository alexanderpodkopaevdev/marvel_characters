package com.alexanderpodkopaev.marvelcharacters.data.model

import com.alexanderpodkopaev.marvelcharacters.data.model.json.CharacterJsonModel


data class CharacterModel(val name: String, val bio: String, val image: String)


fun CharacterJsonModel.toModel(): CharacterModel = CharacterModel(
    name = this.name,
    bio = this.description,
    image = this.thumbnail.path + "." + this.thumbnail.extension
)