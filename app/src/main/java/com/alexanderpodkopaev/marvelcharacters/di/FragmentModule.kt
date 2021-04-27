package com.alexanderpodkopaev.marvelcharacters.di

import com.alexanderpodkopaev.marvelcharacters.ui.CharactersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindCharactersListFragment(): CharactersListFragment
}