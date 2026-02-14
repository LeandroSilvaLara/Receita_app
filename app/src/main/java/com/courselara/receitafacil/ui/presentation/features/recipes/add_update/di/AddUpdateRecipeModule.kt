package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.di

import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.data.repository.AddUpdateRecipeRepositoryImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.data.source.AddUpdateRecipeRemoteDataSourceImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.repository.AddUpdateRecipeRepository
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.source.AddUpdateRecipeRemoteDataSource
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.AddRecipeUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.AddRecipeUseCaseImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.UpdateRecipeUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.UpdateRecipeUseCaseImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.ValidateAddUpdateRecipeInputUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.ValidateAddUpdateRecipeInputUseCaseImpl
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.ValidateDialogInputUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.ValidateDialogInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module responsible for providing dependencies related to the
 * add and update recipe features.
 *
 * This module provides the necessary data sources, repositories, and use cases
 * required for creating new recipes, updating existing ones, and validating
 * user inputs within the recipe management flow.
 */
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
    ): AddUpdateRecipeRepository {
        return AddUpdateRecipeRepositoryImpl(
            remoteDataSource = remoteDataSource,
            dispatcherProvider = dispatcherProvider
        )
    }

    @Provides
    @Singleton
    fun provideAddUpdateRecipeUseCase(
        addUpdateRecipeRepository: AddUpdateRecipeRepository
    ): AddRecipeUseCase {
        return AddRecipeUseCaseImpl(
            addUpdateRecipeRepository = addUpdateRecipeRepository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateRecipeUseCase(
        addUpdateRecipeRepository: AddUpdateRecipeRepository
    ): UpdateRecipeUseCase {
        return UpdateRecipeUseCaseImpl(
            addUpdateRecipeRepository = addUpdateRecipeRepository
        )
    }

    @Provides
    @Singleton
    fun provideValidateAddUpdateRecipeInputUseCase(): ValidateAddUpdateRecipeInputUseCase {
        return ValidateAddUpdateRecipeInputUseCaseImpl()
    }

    @Provides
    @Singleton
    fun provideValidateDialogInputUseCase(): ValidateDialogInputUseCase {
        return ValidateDialogInputUseCaseImpl()
    }

}