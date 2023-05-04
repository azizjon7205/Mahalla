package uz.frankie.mahalla.di

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.frankie.mahalla.BuildConfig
import uz.frankie.mahalla.network.services.GovernorService
import uz.frankie.mahalla.network.services.NotificationService
import uz.frankie.mahalla.utils.Constants
import uz.frankie.mahalla.utils.SharedPreferenceHelper
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
//
//    @[Provides Singleton]
//    fun provideNeighborhoodService(retrofit: Retrofit): NeighborhoodService {
//        return retrofit.create(NeighborhoodService::class.java)
//    }

    @[Provides Singleton]
    fun provideGovernorService(retrofit: Retrofit): GovernorService {
        return retrofit.create(GovernorService::class.java)
    }

    @[Provides Singleton]
    @Named("notification")
    fun provideNotificationService(): NotificationService {
        return Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NotificationService::class.java)

    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        shared: SharedPreferenceHelper,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES)
            .callTimeout(2, TimeUnit.MINUTES).addInterceptor { chain ->
                val request = chain.request()
//                val credentials: String =
//                    Constants.BASIC_USER_NAME_DEBUG + ":" + Constants.BASIC_PASSWORD_DEBUG
//                val basic =
//                    "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
                val newRequest = if (shared.getAccessToken()!! != "empty") request.newBuilder()
                    .addHeader("Authorization", "${shared.getAccessToken()}")
                else request.newBuilder()
                chain.proceed(newRequest.build()).also {
                    if (it.code == 401) {
                        Handler(Looper.getMainLooper()).post { shared.setAccessToken("empty") }
                    }
                }
            }.addInterceptor(chuckerInterceptor).addInterceptor(httpLoggingInterceptor).build()
    }


    @[Provides Singleton]
    fun provideChucker(application: Application): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(application.applicationContext).maxContentLength(250_000L)
            .alwaysReadResponseBody(true).build()
    }

}