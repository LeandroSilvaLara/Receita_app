package com.courselara.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

/**
 * A helper composable that triggers a navigation action based on a conditional state.
 *
 * This function uses a [LaunchedEffect] to monitor the result of [shouldNavigate].
 * When the condition evaluates to true, the [destination] lambda is executed to
 * perform the navigation.
 *
 * @param shouldNavigate A lambda expression that returns a boolean indicating whether
 * the navigation should occur.
 * @param destination A lambda expression containing the navigation logic to be executed.
 */
@Composable
fun NavDestinationHelper(
    shouldNavigate: () -> Boolean,
    destination: () -> Unit
) {
    LaunchedEffect(key1 = shouldNavigate()) {
        if (shouldNavigate()) {
            destination()
        }
    }
}
