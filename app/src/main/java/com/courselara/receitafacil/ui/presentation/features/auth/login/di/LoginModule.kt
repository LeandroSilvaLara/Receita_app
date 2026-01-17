package com.courselara.receitafacil.ui.presentation.features.auth.login.di

import com.courselara.receitafacil.core.data.local.datastore.DataStoreLocalDataSource
import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.ui.presentation.features.auth.login.data.repository.LoginRepositoryImpl
import com.courselara.receitafacil.ui.presentation.features.auth.login.data.source.LoginRemoteDataSourceImpl
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.GetUserDataUseCase
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.GetUserDataUseCaseImpl
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUserCase
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUserCaseImpl
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.RemoveUserDataUseCase
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.RemoveUserDataUseCaseImpl
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.SaveUserDataUseCase
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.SaveUserDataUseCaseImpl
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.ValidateLoginInputUseCase
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.ValidateLoginInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Provides
    fun provideLoginRemoteDataSource(
        recipesServiceApi: RecipesServiceApi
    ): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(recipesServiceApi = recipesServiceApi)
    }

    @Provides
    fun ProvideLoginRepository(
        remoteDataSource: LoginRemoteDataSource,
        localDataSource: DataStoreLocalDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

    fun provideLoginUseCase(
        loginRepository: LoginRepository,
        dispatcherProvider: DispatcherProvider
    ): LoginUserCase {
        return LoginUserCaseImpl(
            loginRepository = loginRepository,
            dispatcherProvider = dispatcherProvider
        )
    }

    @Provides
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCaseImpl()
    }

    @Provides
    fun provideGetUserDataCase(repository: LoginRepository): GetUserDataUseCase {
        return GetUserDataUseCaseImpl(repository)
    }

    @Provides
    fun provideSaveUserDataUseCase(
        repository: LoginRepository,
        dispatcherProvider: DispatcherProvider
    ): SaveUserDataUseCase {
        return SaveUserDataUseCaseImpl(repository, dispatcherProvider)
    }

    @Provides
    fun provideRemoveUserDataCase(repository: LoginRepository): RemoveUserDataUseCase {
        return RemoveUserDataUseCaseImpl(repository)
    }

}


