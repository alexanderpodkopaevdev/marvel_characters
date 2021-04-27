package com.alexanderpodkopaev.marvelcharacters.repository

import com.alexanderpodkopaev.marvelcharacters.data.model.CharacterModel

interface CharacterRepository {
    suspend fun loadCharacters(): List<CharacterModel>
}