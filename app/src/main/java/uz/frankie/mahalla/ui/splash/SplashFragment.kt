package uz.frankie.mahalla.ui.splash

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.messaging.FirebaseMessaging
import uz.frankie.mahalla.R
import uz.frankie.mahalla.utils.extentions.activityNavController
import uz.frankie.mahalla.utils.extentions.navigateSafely
import uz.frankie.mahalla.utils.logger.Logger

class SplashFragment : Fragment(R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countDownTimer()
        loadFCMToken()
    }

    private fun countDownTimer() {
        object : CountDownTimer(2000, 1000){
            override fun onTick(millisUntilFinished: Long) { }

            override fun onFinish() {
                activityNavController().navigateSafely(R.id.action_global_authFlowFragment)
            }
        }.start()
    }

    private fun loadFCMToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful){
                Logger.d("@@@@", "Fetching FCM registration token failed")
                return@addOnCompleteListener
            }
            // Get new FCM registration token
            // Save it in locally to use later
            val token = task.result
            Logger.d("@@@@", token.toString())
//            PrefsManager(this).storeDeviceToken(token.toString())
        }
    }


}