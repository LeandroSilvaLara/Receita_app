package com.courselara.receitafacil.ui.presentation.features.recipes.detail.di

import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.data.repository.RecipeDetailRepositoryImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.data.source.RecipeDetailRemoteDataSourceImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.repository.RecipeDetailRepository
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.source.RecipeDetailRemoteDataSource
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.use_cases.DeleteRecipeUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.use_cases.DeleteRecipeUseCaseImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.use_cases.GetRecipeByIdUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.use_cases.GetRecipeByIdUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeDetailModule {

    @Provides
    @Singleton
    fun provideRecipeDetailRemoteDataSource(
        serviceApi: RecipesServiceApi
    ): RecipeDetailRemoteDataSource {
        return RecipeDetailRemoteDataSourceImpl(
            serviceApi = serviceApi
        )
    }
    @Provides
    @Singleton
    fun provideRecipeDetailRepository(
        remoteDataSource: RecipeDetailRemoteDataSource,
        dispatcherProvider: DispatcherProvider
    ): RecipeDetailRepository {
        return RecipeDetailRepositoryImpl(
            remoteDataSource = remoteDataSource,
            dispatcherProvider = dispatcherProvider
        )
    }

    @Provides
    @Singleton
    fun provideGetRecipeByIdUseCase(
        recipeDetailRepository: RecipeDetailRepository
    ): GetRecipeByIdUseCase {
        return GetRecipeByIdUseCaseImpl(
            recipeDetailRepository = recipeDetailRepository
        )
    }

    @Provides
    @Singleton
    fun provideDeleteRecipeByIdUseCase(
        recipeDetailRepository: RecipeDetailRepository
    ): DeleteRecipeUseCase {
        return DeleteRecipeUseCaseImpl(
            recipeDetailRepository = recipeDetailRepository
        )
    }



}