package com.alexanderpodkopaev.marvelcharacters.di

import com.alexanderpodkopaev.marvelcharacters.repository.CharacterRepository
import com.alexanderpodkopaev.marvelcharacters.repository.NetworkCharacterRepoImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(repository: NetworkCharacterRepoImpl): CharacterRepository
}