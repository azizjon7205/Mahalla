package uz.frankie.mahalla

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.frankie.mahalla.utils.NotificationManager


@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        NotificationManager.init(this)
    }

}