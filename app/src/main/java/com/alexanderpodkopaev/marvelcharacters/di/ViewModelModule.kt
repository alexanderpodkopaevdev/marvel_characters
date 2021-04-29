package com.alexanderpodkopaev.marvelcharacters.di

import androidx.lifecycle.ViewModel
import com.alexanderpodkopaev.marvelcharacters.ui.CharactersListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersListViewModel::class)
    abstract fun provideCharactersListViewModel(viewModel: CharactersListViewModel): ViewModel
}