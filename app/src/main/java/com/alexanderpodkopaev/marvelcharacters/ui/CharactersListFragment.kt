package com.alexanderpodkopaev.marvelcharacters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.marvelcharacters.R
import com.alexanderpodkopaev.marvelcharacters.di.ViewModelFactory
import com.alexanderpodkopaev.marvelcharacters.repository.CharacterRepository
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersListFragment : DaggerFragment() {

    @Inject
    lateinit var repository: CharacterRepository

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: CharactersListViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnRetry: Button

    private var adapter = CharacterAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters_list, container, false)
        initView(view)
        initRecycler(view)
        viewModel = ViewModelProvider(this, viewModelFactory)[CharactersListViewModel::class.java]
        btnRetry.setOnClickListener { adapter.retry() }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.characters.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initView(view: View) {
        btnRetry = view.findViewById(R.id.btn_retry_main)
        recyclerView = view.findViewById(R.id.rv_characters)
        progressBar = view.findViewById(R.id.progressbar_main)

    }

    private fun initRecycler(view: View) {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(CharactersLoadStateAdapter { adapter.retry() },CharactersLoadStateAdapter { adapter.retry() })

        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            btnRetry.isVisible = loadState.source.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
                ?: loadState.source.refresh as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    requireContext(),
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


}