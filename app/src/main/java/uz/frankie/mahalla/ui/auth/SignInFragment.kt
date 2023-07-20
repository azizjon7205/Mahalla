package uz.frankie.mahalla.ui.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentSignInBinding
import uz.frankie.mahalla.model.FCMNote
import uz.frankie.mahalla.model.LoginRequest
import uz.frankie.mahalla.model.Notification
import uz.frankie.mahalla.utils.SharedPreferenceHelper
import uz.frankie.mahalla.utils.extentions.activityNavController
import uz.frankie.mahalla.utils.extentions.collectLA
import uz.frankie.mahalla.utils.extentions.getJson
import uz.frankie.mahalla.utils.extentions.navigateSafely
import uz.frankie.mahalla.utils.logger.Logger
import uz.frankie.mahalla.viewmodels.AuthViewModel
import uz.frankie.mahalla.viewmodels.NotificationVM
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val authViewModel: AuthViewModel by viewModels()
    private val binding by viewBinding(FragmentSignInBinding::bind)
    private var role = "hokim"
    private var token: String? = null
    private var loginRequest: LoginRequest? = null
    @Inject
    lateinit var preferences: SharedPreferenceHelper


    @SuppressLint("HardwareIds")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFCMToken()
        collectUiState()

        // Fetching Android ID and storing it into a constant
        val mId = Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)


        binding.apply {

            userName.addTextChangedListener {userName ->
                passwordEt.addTextChangedListener {
                    binding.btnSignIn.isEnabled = !it.isNullOrEmpty() && !userName.isNullOrEmpty()
                }
            }

        }

        binding.btnSignIn.setOnClickListener {

            loginRequest = LoginRequest("${binding.userName.text}", binding.passwordEt.text.toString(), token.toString(), mId)

            Logger.d("@@@", "Login data: ${loginRequest}")
            authViewModel.login(loginRequest!!)

        }

    }

    private fun loadFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Logger.d("@@@@", "Fetching FCM registration token failed")
                return@addOnCompleteListener
            }
            // Get new FCM registration token
            // Save it in locally to use later
            token = task.result
            Logger.d("@@@@", token.toString())
//            PrefsManager(this).storeDeviceToken(token.toString())
        }
    }

    private fun collectUiState(){

        authViewModel.uiState.collectLA(viewLifecycleOwner){ uiState ->
            if (uiState.data != null){
                val loginResponse = uiState.data
                role = preferences.getRole()!!


                Toast.makeText(requireActivity(), "Signed in successfully", Toast.LENGTH_SHORT).show()

                Logger.d("@@@", "Login Success data: ${loginResponse}")
                Logger.d("@@@", "Login Success data: $role ::: ${getJson(loginResponse.access.split(".")[1])}")
                when (loginResponse.role) {
                    "hokim" -> {
                        activityNavController().navigateSafely(R.id.action_global_governorFlowFragment)
                    }
                    "rais" -> activityNavController().navigateSafely(R.id.action_global_villagesFlowFragment)
                    "worker" -> activityNavController().navigateSafely(R.id.action_global_governorAssistantFlowFragment)
                }
            }
            Logger.d("@@@", "Login Error ${uiState.errorMessage}")
        }
    }

}