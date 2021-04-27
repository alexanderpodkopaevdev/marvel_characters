package com.alexanderpodkopaev.marvelcharacters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.marvelcharacters.data.model.CharacterModel
import com.alexanderpodkopaev.marvelcharacters.repository.CharacterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(val repository: CharacterRepository) :
    ViewModel() {

    private val _charactersList = MutableLiveData<List<CharacterModel>>(emptyList())
    val charactersList: LiveData<List<CharacterModel>> = _charactersList

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchCharacters() {
        viewModelScope.launch {
            _isLoading.value = true
            _charactersList.value = repository.loadCharacters()
            _isLoading.value = false
        }
    }
}