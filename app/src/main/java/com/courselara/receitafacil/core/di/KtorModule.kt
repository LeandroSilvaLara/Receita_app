package com.courselara.receitafacil.core.di


import com.courselara.receitafacil.BuildConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.gson.gson
import okhttp3.OkHttpClient
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        okHttpClient: OkHttpClient,
    ) : HttpClient {
        return HttpClient(OkHttp) {

            defaultRequest {
                url(BuildConfig.BASE_URL)
            }
            engine {
                preconfigured = okHttpClient
                config {

                }
            }

            install(Logging) {
                logger = object : Logger{
                    override fun log(message: String) {
                        Timber.tag("Logger ktor -> ").v(message)
                    }
                }
                level = LogLevel.INFO
            }

            install(ContentNegotiation) {
                gson {
                    setLenient()
                    setPrettyPrinting()
                    serializeNulls()
                }
            }

            install(WebSockets) {

            }

        }

    }
}