package com.courselara.receitafacil.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.courselara.receitafacil.core.util.logging.LogInfo
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.GetUserDataUseCase
import com.courselara.receitafacil.ui.presentation.navigation.screens.Graphs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getUserDataUserCase: GetUserDataUseCase,
): ViewModel() {

    private val _isSplashLoading = MutableStateFlow(true)
    val isSplashLoading = _isSplashLoading.asStateFlow()

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            getUserDataUserCase.invoke().collect { userData ->
                if (userData.token.isNotEmpty()) {
                    _uiState.update { it.copy(startDestination = Graphs.HomeGraph) }
                } else if (!userData.errorMessage.isNullOrEmpty()) {
                    LogInfo("USER_DATA", "UserData: ${userData.errorMessage}")
                }
                delay(1000)
                _isSplashLoading.update { false }
            }
        }
    }
}