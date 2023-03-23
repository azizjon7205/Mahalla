package uz.frankie.mahalla.repositories

import uz.frankie.mahalla.network.services.NotificationService
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val notificationService: NotificationService
) {
}