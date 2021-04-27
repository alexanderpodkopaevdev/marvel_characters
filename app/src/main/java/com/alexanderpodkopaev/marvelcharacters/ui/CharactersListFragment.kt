package com.alexanderpodkopaev.marvelcharacters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.marvelcharacters.CharacterAdapter
import com.alexanderpodkopaev.marvelcharacters.CharactersListViewModel
import com.alexanderpodkopaev.marvelcharacters.R
import com.alexanderpodkopaev.marvelcharacters.di.ViewModelFactory
import com.alexanderpodkopaev.marvelcharacters.repository.CharacterRepository
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CharactersListFragment : DaggerFragment() {

    @Inject
    lateinit var repository: CharacterRepository

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: CharactersListViewModel

    lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters_list, container, false)
        initRecycler(view)
        viewModel = ViewModelProvider(this, viewModelFactory)[CharactersListViewModel::class.java]
        viewModel.charactersList.observe(viewLifecycleOwner) {
            adapter.bindCharacters(it)
        }
        viewModel.fetchCharacters()
        return view
    }

    private fun initRecycler(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_characters)
        adapter = CharacterAdapter()
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
    }


}