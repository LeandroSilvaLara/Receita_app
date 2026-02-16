package com.courselara.receitafacil.ui.presentation.features.recipes.search.data

import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.ui.presentation.features.recipes.search.data.repository.SearchRecipesRepositoryImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.search.data.source.SearchRecipesRemoteDataSourceImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.search.domain.repository.SearchRecipesRepository
import com.courselara.receitafacil.ui.presentation.features.recipes.search.domain.source.SearchRecipesRemoteDataSource
import com.courselara.receitafacil.ui.presentation.features.recipes.search.domain.usecases.SearchRecipeUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.search.domain.usecases.SearchRecipeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchRecipesModule {

    @Provides
    @Singleton
    fun provideSearchRecipesRemoteDataSource(
        serviceApi: RecipesServiceApi
    ): SearchRecipesRemoteDataSource {

        return SearchRecipesRemoteDataSourceImpl(
            serviceApi = serviceApi
        )
    }

    @Provides
    @Singleton
    fun provideSearchRecipesRepository(
        remoteDataSource: SearchRecipesRemoteDataSource,
        dispatcherProvider: DispatcherProvider
    ): SearchRecipesRepository {
        return SearchRecipesRepositoryImpl(
            remoteDataSource = remoteDataSource,
            dispatcherProvider = dispatcherProvider
        )
    }

    @Provides
    @Singleton
    fun provideSearchRecipeUseCase(
        searchRecipesRepository: SearchRecipesRepository
    ): SearchRecipeUseCase {
        return SearchRecipeUseCaseImpl(
            searchRecipesRepository = searchRecipesRepository
        )
    }
}