package com.courselara.receitafacil.core.data.local.datastore

import com.courselara.receitafacil.core.domain.model.UseData
import kotlinx.coroutines.flow.Flow

interface DataStoreLocalDataSource {
    fun getData(): Flow<UseData>
    suspend fun saveData(token: String, userName: String)
    suspend fun clearAll()
}