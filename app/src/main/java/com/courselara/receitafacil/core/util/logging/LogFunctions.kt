package com.courselara.receitafacil.core.util.logging

import timber.log.Timber

fun logError(tag: String, message: String) {
    Timber.tag(tag).e("Error: $message")
}

fun LogInfo(tag: String, message: String) {
    Timber.tag(tag).i("Info: $message")
}

fun LogWarning(tag: String, message: String) {
    Timber.tag(tag).w("Warning: $message")
}
