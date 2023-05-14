package uz.frankie.mahalla.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.frankie.mahalla.data.MyDatabase
import uz.frankie.mahalla.data.dao.MyselfDao
import uz.frankie.mahalla.utils.MySelfRepository
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

    // Provide MyselfDao first
    @Provides
    @Singleton
    fun provideMyselfDao(application: Application): MyselfDao {
        return MyDatabase.getInstance(application).myselfDao()
    }

    // Provide MySelfRepository
    @Provides
    @Singleton
    fun provideMySelfRepository(myDao: MyselfDao): MySelfRepository {
        return MySelfRepository(myDao)
    }

}