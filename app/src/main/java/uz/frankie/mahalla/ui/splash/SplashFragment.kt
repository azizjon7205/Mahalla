package uz.frankie.mahalla.ui.splash

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import uz.frankie.mahalla.R
import uz.frankie.mahalla.utils.SharedPreferenceHelper
import uz.frankie.mahalla.utils.extentions.activityNavController
import uz.frankie.mahalla.utils.extentions.collectLA
import uz.frankie.mahalla.utils.extentions.navigateSafely
import uz.frankie.mahalla.utils.logger.Logger
import uz.frankie.mahalla.viewmodels.SplashViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    @Inject
    lateinit var preferences: SharedPreferenceHelper


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Logger.d("RRR", "Splash >> ${preferences.getRole()}")
        countDownTimer()
        loadFCMToken()
    }

    private fun countDownTimer() {
        object : CountDownTimer(2000, 1000){
            override fun onTick(millisUntilFinished: Long) { }

            override fun onFinish() {
                navigateToNext()
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
        }
    }

    private fun navigateToNext() {
        when (preferences.getRole()) {
                "empty" -> activityNavController().navigateSafely(R.id.action_global_authFlowFragment)
                "hokim" -> activityNavController().navigateSafely(R.id.action_global_governorFlowFragment)
                "rais" -> activityNavController().navigateSafely(R.id.action_global_villagesFlowFragment)
                "worker" -> activityNavController().navigateSafely(R.id.action_global_governorAssistantFlowFragment)
                else -> activityNavController().navigateSafely(R.id.action_global_authFlowFragment)

            }

    }

}