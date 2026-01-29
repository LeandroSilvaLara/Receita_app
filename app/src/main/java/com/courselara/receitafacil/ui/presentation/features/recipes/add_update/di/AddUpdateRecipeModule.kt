package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.di

import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.data.repository.AddUpdateRecipeRepositoryImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.data.source.AddUpdateRecipeRemoteDataSourceImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.repository.AddUpdateRecipeRepository
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.source.AddUpdateRecipeRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddUpdateRecipeModule {

    @Provides
    @Singleton
    fun provideAddUpdateRecipeRemoteDataSource(
        serviceApi: RecipesServiceApi
    ): AddUpdateRecipeRemoteDataSource {
        return AddUpdateRecipeRemoteDataSourceImpl(
            serviceApi = serviceApi
        )

    }

    @Provides
    @Singleton
    fun provideAddUpdateRecipeRepository(
        remoteDataSource: AddUpdateRecipeRemoteDataSource,
        dispatcherProvider: DispatcherProvider
    ) : AddUpdateRecipeRepository {
        return AddUpdateRecipeRepositoryImpl(
            remoteDataSource = remoteDataSource,
            dispatcherProvider = dispatcherProvider
        )
    }

}