package com.courselara.receitafacil.core.data.remote

import com.courselara.receitafacil.core.data.local.datastore.DataStoreLocalDataSource
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.core.util.logging.LogInfo
import com.courselara.receitafacil.core.util.logging.logError
import jakarta.inject.Inject
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor @Inject constructor(
    private val localDataSource: DataStoreLocalDataSource,
    private val dispatcherProvider: DispatcherProvider
) : Interceptor {

    companion object{
        const val TOKEN_TYPE = "Bearer"
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val data = try {
            runBlocking(dispatcherProvider.io()) {
                localDataSource.getData().firstOrNull()
            }
        } catch (e: Exception) {
            logError("INTERCEPTOR", "Token não encontrado")
            null
        }

        val request = chain.request().newBuilder()
        data?.token?.let { token ->
            request.addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
        } ?: run {
            LogInfo("INTERCEPTOR", "Token não encontrado")
        }

        return chain.proceed(request.build())
    }

}