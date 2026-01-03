package com.courselara.receitafacil.ui.presentation.features.register.di

import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.ui.presentation.features.register.data.repository.RegisterUserRepositoryImpl
import com.courselara.receitafacil.ui.presentation.features.register.data.source.RegisterUserRemoteDataSourceImpl
import com.courselara.receitafacil.ui.presentation.features.register.domain.repository.RegisterUserRepository
import com.courselara.receitafacil.ui.presentation.features.register.domain.source.RegisterUserRemoteDataSource
import com.courselara.receitafacil.ui.presentation.features.register.domain.usecase.RegisterUserUserCase
import com.courselara.receitafacil.ui.presentation.features.register.domain.usecase.RegisterUserUserCaseImpl
import com.courselara.receitafacil.ui.presentation.features.register.domain.usecase.ValidateRegisterInputUseCase
import com.courselara.receitafacil.ui.presentation.features.register.domain.usecase.ValidateRegisterInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegisterUserModule {

    @Provides
    @Singleton
    fun provideRegisterUserDataSource(
        recipesServiceAPI: RecipesServiceApi
    ) : RegisterUserRemoteDataSource {
        return RegisterUserRemoteDataSourceImpl(recipesServiceAPI = recipesServiceAPI)
    }

    @Provides
    @Singleton
    fun provideRegisterUserRepository(
        registerUserRemoteDataSource: RegisterUserRemoteDataSource
    ) : RegisterUserRepository {
        return RegisterUserRepositoryImpl(remoteDataSource = registerUserRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRegisterUserUseCase(
        registerUserRepository: RegisterUserRepository,
        dispatcherProvider: DispatcherProvider
    ) : RegisterUserUserCase {
        return RegisterUserUserCaseImpl(
            registerUserRepository = registerUserRepository,
            dispatcherProvider = dispatcherProvider

        )
    }

    @Provides
    @Singleton
    fun provideValidateRegisterInputUseCase(

    ) : ValidateRegisterInputUseCase {
        return ValidateRegisterInputUseCaseImpl()
    }
}