package uz.frankie.mahalla.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.frankie.mahalla.utils.SharedPreferenceHelper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun getSharedPreferences(@ApplicationContext context: Context): SharedPreferenceHelper = SharedPreferenceHelper(context = context)

    @Provides
    @Singleton
    fun provideGson() = Gson()

}