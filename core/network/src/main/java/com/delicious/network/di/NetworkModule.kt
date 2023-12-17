package com.delicious.network.di

import android.app.Application
import com.delicious.network.BuildConfig
import com.delicious.network.callAdapter.NetworkResponseAdapterFactory
import com.delicious.network.interceptor.ApiKeyQueryParamInterceptor
import com.delicious.network.interceptor.ForceCacheInterceptor
import com.delicious.network.interceptor.HttpCacheInterceptor
import com.delicious.network.utils.NetworkConnection
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpCacheInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpForceCacheInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpAddApiKeyHeaderInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L
    private const val CONNECTION_TIMEOUT = 10L


    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        context: Application,
        @OkHttpCacheInterceptor cacheInterceptor: Interceptor,
        @OkHttpForceCacheInterceptor forceCacheInterceptor: Interceptor,
        @OkHttpAddApiKeyHeaderInterceptor queryParamInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .cache(Cache(File(context.cacheDir, "http-cache"), 10L * 1024L * 1204L)) // 10MiB
            .addNetworkInterceptor(cacheInterceptor)
            .addInterceptor(queryParamInterceptor)
            .addInterceptor(forceCacheInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
    }

    @Provides
    @Singleton
    @OkHttpCacheInterceptor
    fun provideHttpCacheInterceptor(): Interceptor =
        HttpCacheInterceptor()

    @Provides
    @Singleton
    @OkHttpForceCacheInterceptor
    internal fun provideForceCacheInterceptor(networkConnection: NetworkConnection): Interceptor =
        ForceCacheInterceptor(networkConnection)

    @Provides
    @Singleton
    @OkHttpAddApiKeyHeaderInterceptor
    fun provideAddApiKeyHeaderInterceptor(): Interceptor =
        ApiKeyQueryParamInterceptor()

}