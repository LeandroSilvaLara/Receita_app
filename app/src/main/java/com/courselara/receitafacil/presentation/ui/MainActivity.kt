package com.courselara.receitafacil.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.courselara.receitafacil.presentation.navigation.RootHost
import com.courselara.receitafacil.presentation.navigation.screens.Graphs
import com.courselara.receitafacil.presentation.ui.theme.ReceitaFacilAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ReceitaFacilAppTheme {

                val navController : NavHostController = rememberNavController()

                RootHost(
                    startDestination = Graphs.AuthGraph,
                    navController = navController
                )
            }
        }
    }
}
