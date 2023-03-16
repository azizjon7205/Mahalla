package uz.frankie.mahalla.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import uz.frankie.mahalla.network.services.NotificationService
import uz.frankie.mahalla.utils.Constants.NOTIF_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {

    @[Provides Singleton]
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit{
        return Retrofit.Builder().baseUrl(NOTIF_URL).client(client)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().setLenient().create()
                )
            ).build()
    }

    @[Provides Singleton]
    fun provideNotificationService(retrofit: Retrofit): NotificationService{
        return retrofit.create(NotificationService::class.java)
    }
}