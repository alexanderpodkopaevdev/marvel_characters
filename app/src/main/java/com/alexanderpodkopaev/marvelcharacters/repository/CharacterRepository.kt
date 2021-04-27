package com.alexanderpodkopaev.marvelcharacters.repository

import com.alexanderpodkopaev.marvelcharacters.model.CharacterModel

interface CharacterRepository {
    suspend fun loadCharacters(): List<CharacterModel>
}