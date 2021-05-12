package com.alexanderpodkopaev.marvelcharacters.repository

import androidx.paging.PagingData
import com.alexanderpodkopaev.marvelcharacters.data.model.CharacterModel
import com.alexanderpodkopaev.marvelcharacters.data.model.json.CharacterJsonModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun loadCharacters(): Flow<PagingData<CharacterJsonModel>>
}