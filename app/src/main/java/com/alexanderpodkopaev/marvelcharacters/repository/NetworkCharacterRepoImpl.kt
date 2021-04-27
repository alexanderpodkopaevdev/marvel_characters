package com.alexanderpodkopaev.marvelcharacters.repository

import com.alexanderpodkopaev.marvelcharacters.BuildConfig
import com.alexanderpodkopaev.marvelcharacters.data.CharacterApi
import com.alexanderpodkopaev.marvelcharacters.data.model.CharacterModel
import com.alexanderpodkopaev.marvelcharacters.data.model.toModel
import com.alexanderpodkopaev.marvelcharacters.utils.md5
import javax.inject.Inject

class NetworkCharacterRepoImpl @Inject constructor(val characterApi: CharacterApi) :
    CharacterRepository {
    override suspend fun loadCharacters(): List<CharacterModel> {
        val hash = ("1" + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY).md5()
        return characterApi.getCharacters(
            "1",
            BuildConfig.PUBLIC_KEY,
            hash,
            20
        ).data.characterJsonModels.map {
            it.toModel()
        }
    }
}