package com.courselara.receitafacil.core.data.local.datastore

import android.service.autofill.UserData
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.courselara.receitafacil.core.domain.model.UseData
import com.courselara.receitafacil.core.util.logging.LogInfo
import com.courselara.receitafacil.core.util.logging.logError
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreLocalDataSourceImpl @Inject constructor(
    private val dataStorePreferences: DataStore<Preferences>
) : DataStoreLocalDataSource {

    private object PreferencesKeys {
        val TOKEN_KEY = stringPreferencesKey("token")
        val USER_NAME_KEY = stringPreferencesKey("user_name")
    }


    override fun getData(): Flow<UseData> {
        return dataStorePreferences.data.catch { error ->
            error.localizedMessage?.let { logError("DATA_STORE_ERROR", it) }
            emit(emptyPreferences())
        }.map { preferences ->
            val token = preferences[PreferencesKeys.TOKEN_KEY] ?: ""
            val userName = preferences[PreferencesKeys.USER_NAME_KEY] ?: ""
            UseData(token = token, userName = userName)
        }
    }

    override suspend fun saveData(token: String, userName: String) {
        try {
            LogInfo("DataStore", "token: $token - Usuario: $userName")
            dataStorePreferences.edit { preferences ->
                preferences[PreferencesKeys.TOKEN_KEY] = token
                preferences[PreferencesKeys.USER_NAME_KEY] = userName
            }
            LogInfo("DataStore", "Dados Salvos com sucesso")
        }catch (e: Exception){
            LogInfo("DataStore", "Ocorreu um erro: ${e.message}")
        }
    }

    override suspend fun clearAll() {
        dataStorePreferences.edit { preferences ->
            preferences.clear()
        }
    }
}
