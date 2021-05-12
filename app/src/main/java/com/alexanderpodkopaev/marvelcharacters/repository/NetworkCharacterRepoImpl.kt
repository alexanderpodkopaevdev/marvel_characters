package com.alexanderpodkopaev.marvelcharacters.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alexanderpodkopaev.marvelcharacters.data.MarvelApi
import com.alexanderpodkopaev.marvelcharacters.data.MarvelPagingSource
import com.alexanderpodkopaev.marvelcharacters.data.model.json.CharacterJsonModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NetworkCharacterRepoImpl @Inject constructor(val marvelApi: MarvelApi) :
    CharacterRepository {
    override fun loadCharacters(): Flow<PagingData<CharacterJsonModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MarvelPagingSource(marvelApi) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}

