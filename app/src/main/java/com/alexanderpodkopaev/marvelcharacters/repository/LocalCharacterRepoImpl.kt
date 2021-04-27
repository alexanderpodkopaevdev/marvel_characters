package com.alexanderpodkopaev.marvelcharacters.repository

import com.alexanderpodkopaev.marvelcharacters.model.CharacterModel
import javax.inject.Inject

class LocalCharacterRepoImpl @Inject constructor() : CharacterRepository {
    override suspend fun loadCharacters(): List<CharacterModel> {
        return listOf(
            CharacterModel(
                "3-D Man",
                "",
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ),
            CharacterModel(
                "3-D Man",
                "",
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ),
            CharacterModel(
                "3-D Man",
                "",
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ),
            CharacterModel(
                "3-D Man",
                "",
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ),
            CharacterModel(
                "3-D Man",
                "",
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            )
        )
    }
}