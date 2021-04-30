package com.alexanderpodkopaev.marvelcharacters.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alexanderpodkopaev.marvelcharacters.BuildConfig
import com.alexanderpodkopaev.marvelcharacters.data.model.json.CharacterJsonModel
import com.alexanderpodkopaev.marvelcharacters.utils.md5
import retrofit2.HttpException
import java.io.IOException

private const val MARVEL_STARTING_CHARACTER = 0

class MarvelPagingSource(private val marvelApi: MarvelApi) :
    PagingSource<Int, CharacterJsonModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterJsonModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterJsonModel> {
        val position = params.key ?: MARVEL_STARTING_CHARACTER
        return try {
            val response = marvelApi.getCharacters(
                position,
                params.loadSize
            )
            val characters = response.data.characterJsonModels
            val nextKey = if (characters.isEmpty()) {
                null
            } else {
                position + params.loadSize
            }
            LoadResult.Page(
                data = characters,
                prevKey = if (position == MARVEL_STARTING_CHARACTER) null else position - 1,
                nextKey = nextKey
            )
        } catch (ex: IOException) {
            return LoadResult.Error(ex)
        } catch (ex: HttpException) {
            return LoadResult.Error(ex)
        }
    }
}