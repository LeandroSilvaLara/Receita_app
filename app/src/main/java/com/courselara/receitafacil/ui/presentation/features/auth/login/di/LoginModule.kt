package com.courselara.receitafacil.ui.presentation.features.auth.login.di

import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.ui.presentation.features.auth.login.data.repository.LoginRepositoryImpl
import com.courselara.receitafacil.ui.presentation.features.auth.login.data.source.LoginRemoteDataSourceImpl
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUserCase
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUserCaseImpl
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
    ) : LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(recipesServiceApi = recipesServiceApi)
    }

    @Provides
    fun ProvideLoginRepository(
        remoteDataSource: LoginRemoteDataSource
    ) : LoginRepository {
        return LoginRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    fun provideLoginUseCase(
        loginRepository: LoginRepository,
        dispatcherProvider: DispatcherProvider
    ) : LoginUserCase {
        return LoginUserCaseImpl(
            loginRepository = loginRepository,
            dispatcherProvider = dispatcherProvider
        )
    }

    @Provides
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCaseImpl()
    }
}


