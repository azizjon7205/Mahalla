package uz.frankie.mahalla.utils

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService

class NotificationManager {
    companion object{
        private lateinit var context: Context
        fun init(context: Context){
            this.context = context
        }

        fun showNotification(
            title: String?,
            message: String?
        ) {
            val pm = context.packageManager
            val appInfo = pm.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            val appIcon = appInfo.icon


            // Pass the intent to switch to the MainActivity
            //val intent = Intent(this, MainActivity::class.java)
            // Assign channel ID
            val channel_id = "notification_channel"

            // Create a Builder object using NotificationCompat
            // class. This will allow control over all the flags
            var builder: NotificationCompat.Builder = NotificationCompat.Builder(
                context,
                channel_id
            )
                .setSmallIcon(appIcon)
                .setAutoCancel(true)
                .setVibrate(
                    longArrayOf(
                        1000, 1000, 1000,
                        1000, 1000
                    )
                )
                .setOnlyAlertOnce(true)

            builder.setContentTitle(title)
            builder.setContentText(message)


            // Create an object of NotificationManager class to
            // notify the
            // user of events that happen in the background.
            val notificationManager = context.getSystemService(
                FirebaseMessagingService.NOTIFICATION_SERVICE
            ) as NotificationManager
            // Check if the Android Version is greater than Oreo
            if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.O
            ) {
                val notificationChannel = NotificationChannel(
                    channel_id, "web_app",
                    NotificationManager.IMPORTANCE_HIGH
                )

                notificationManager.createNotificationChannel(
                    notificationChannel
                )
            }

            notificationManager.notify(0, builder.build())
        }
    }

}