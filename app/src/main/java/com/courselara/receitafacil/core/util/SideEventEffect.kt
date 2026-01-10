package com.courselara.receitafacil.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.courselara.receitafacil.core.sideeffects.SideEffect
import kotlinx.coroutines.flow.Flow

/**
 * A Composable that collects events from a [Flow] in a lifecycle-aware manner.
 *
 * This function is typically used for handling one-time side effects (like navigation, showing
 * snackbars, or triggering dialogs) without missing events during configuration changes or
 * when the app is in the background. It utilizes [repeatOnLifecycle] to ensure collection
 * only occurs when the lifecycle is at least in the specified [lifecycleState].
 *
 * @param T The type of the side effect event.
 * @param sideEffectFlow The [Flow] of events to be collected.
 * @param lifecycleState The minimum [Lifecycle.State] required for the flow to be collected.
 * Defaults to [Lifecycle.State.STARTED].
 * @param collector The callback invoked whenever a new event is emitted by the [sideEffectFlow].
 */
@Composable
fun <T : Any> SingleEventEffect(
    sideEffectFlow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    collector: (T) -> Unit

) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(sideEffectFlow) {
        lifecycleOwner.repeatOnLifecycle(lifecycleState) {
            sideEffectFlow.collect(collector)
        }
    }
}