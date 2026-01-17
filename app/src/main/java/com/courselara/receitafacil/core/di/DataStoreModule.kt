package com.courselara.receitafacil.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.courselara.receitafacil.core.data.local.datastore.DataStoreLocalDataSource
import com.courselara.receitafacil.core.data.local.datastore.DataStoreLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    fun provideDataStoreData(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        produceFile = {
            context.preferencesDataStoreFile("recipes_prefs")
        }
    )

    fun provideDataStoreLocalDataSource(
        dataStorePreferences: DataStore<Preferences>
    ): DataStoreLocalDataSource {
        return DataStoreLocalDataSourceImpl(dataStorePreferences = dataStorePreferences)

    }

}