package com.alexanderpodkopaev.marvelcharacters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.marvelcharacters.CharacterAdapter
import com.alexanderpodkopaev.marvelcharacters.R
import com.alexanderpodkopaev.marvelcharacters.model.CharacterModel
import dagger.android.support.DaggerFragment

class CharactersListFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters_list, container, false)
        initRecycler(view)
        return view
    }

    private fun initRecycler(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_characters)
        val adapter = CharacterAdapter()
        adapter.bindCharacters(fetchCharacter())
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
    }

    private fun fetchCharacter(): List<CharacterModel> {
        return listOf(
            CharacterModel("3-D Man", "", "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            CharacterModel("3-D Man", "", "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            CharacterModel("3-D Man", "", "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            CharacterModel("3-D Man", "", "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            CharacterModel("3-D Man", "", "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg")
        )
    }
}