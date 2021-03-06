package com.alexanderpodkopaev.marvelcharacters.di

import android.content.Context
import com.alexanderpodkopaev.marvelcharacters.MarvelApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityModule::class, RepositoryModule::class, ViewModelModule::class, NetworkModule::class])
interface AppComponent : AndroidInjector<MarvelApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}