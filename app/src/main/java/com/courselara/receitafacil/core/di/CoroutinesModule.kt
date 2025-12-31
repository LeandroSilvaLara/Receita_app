package com.courselara.receitafacil.core.di

import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.core.util.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {

    @Binds
    fun providesDispatchersProvider(dispatcherProvider: DispatcherProviderImpl): DispatcherProvider

}