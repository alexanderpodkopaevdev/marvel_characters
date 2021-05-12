package com.alexanderpodkopaev.marvelcharacters.di

import com.alexanderpodkopaev.marvelcharacters.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bindMainActivity(): MainActivity
}