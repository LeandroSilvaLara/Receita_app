package com.courselara.receitafacil.ui.presentation.features.recipes.list.di

import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.ui.presentation.features.recipes.list.data.repository.GetRecipesByUserRepositoryImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.list.data.source.GetRecipesByUserRemoteDataSourceImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.list.domain.repository.GetRecipesByUserRepository
import com.courselara.receitafacil.ui.presentation.features.recipes.list.domain.source.GetRecipesByUserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ListRecipesModule {

    @Provides
    @Singleton
    fun provideGetRecipesByUserRemoteDataSource(
        recipesServiceApi: RecipesServiceApi
    ): GetRecipesByUserRemoteDataSource {
        return GetRecipesByUserRemoteDataSourceImpl(
            recipesServiceApi = recipesServiceApi
        )
    }

    @Provides
    @Singleton
    fun provideGetRecipesByUserRepository(
        remoteDataSource: GetRecipesByUserRemoteDataSource,
        dispatcherProvider: DispatcherProvider
    ): GetRecipesByUserRepository {
        return GetRecipesByUserRepositoryImpl(
            remoteDataSource = remoteDataSource,
            dispatcherProvider = dispatcherProvider
        )
    }
}