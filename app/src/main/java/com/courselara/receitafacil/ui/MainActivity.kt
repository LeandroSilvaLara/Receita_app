package com.courselara.receitafacil.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.courselara.receitafacil.ui.presentation.navigation.RootHost
import com.courselara.receitafacil.ui.presentation.navigation.screens.Graphs
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isSplashLoading.value
        }

        setContent {
            ReceitaFacilAppTheme {

                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                val navController : NavHostController = rememberNavController()

                RootHost(
                    startDestination = uiState.startDestination,
                    navController = navController
                )
            }
        }
    }
}