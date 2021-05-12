package com.alexanderpodkopaev.marvelcharacters

import com.alexanderpodkopaev.marvelcharacters.di.AppComponent
import com.alexanderpodkopaev.marvelcharacters.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MarvelApp : DaggerApplication() {
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }
}