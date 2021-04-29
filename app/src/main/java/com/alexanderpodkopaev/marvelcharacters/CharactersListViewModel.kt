package com.alexanderpodkopaev.marvelcharacters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.alexanderpodkopaev.marvelcharacters.data.model.toModel
import com.alexanderpodkopaev.marvelcharacters.repository.CharacterRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(val repository: CharacterRepository) :
    ViewModel() {

    val characters = repository.loadCharacters().map { it.map { model -> model.toModel() } }
        .cachedIn(viewModelScope)

}