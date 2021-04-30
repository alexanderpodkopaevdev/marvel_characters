package com.alexanderpodkopaev.marvelcharacters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.marvelcharacters.R
import com.alexanderpodkopaev.marvelcharacters.data.model.CharacterModel
import com.alexanderpodkopaev.marvelcharacters.di.ViewModelFactory
import com.alexanderpodkopaev.marvelcharacters.repository.CharacterRepository
import com.alexanderpodkopaev.marvelcharacters.utils.OffsetItemDecoration
import com.alexanderpodkopaev.marvelcharacters.utils.UiUtils
import com.google.android.material.transition.MaterialElevationScale
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersListFragment : DaggerFragment(), CharacterClickListener {

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
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.characters.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onClick(view: View, character: CharacterModel) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        val action =
            CharactersListFragmentDirections.actionCharactersListFragmentToCharacterFragment(
                character
            )
        val extras = FragmentNavigatorExtras(view to character.name)
        findNavController().navigate(action, extras)
    }

    private fun initView(view: View) {
        btnRetry = view.findViewById(R.id.btn_retry_main)
        recyclerView = view.findViewById(R.id.rv_characters)
        progressBar = view.findViewById(R.id.progressbar_main)

    }

    private fun initRecycler(view: View) {
        recyclerView.layoutManager = GridLayoutManager(
            context,
            UiUtils.calculateNoOfColumns(
                requireContext(),
                requireContext().resources.getDimension(R.dimen.character_width)
                        + requireContext().resources.getDimension(R.dimen.doubleStandard)
            )
        )
        recyclerView.addItemDecoration(
            OffsetItemDecoration(
                requireContext().resources.getDimension(
                    R.dimen.standard
                ).toInt()
            )
        )
        recyclerView.adapter =
            adapter.withLoadStateHeaderAndFooter(CharactersLoadStateAdapter { adapter.retry() },
                CharactersLoadStateAdapter { adapter.retry() })

        adapter.addLoadStateListener { loadState ->
            recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            progressBar.isVisible = loadState.source.refresh is LoadState.Loading
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
        adapter.clickListener = this
    }


}