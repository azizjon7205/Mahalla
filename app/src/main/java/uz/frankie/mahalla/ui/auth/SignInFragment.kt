package uz.frankie.mahalla.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import uz.frankie.mahalla.MainActivity
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentSignInBinding
import uz.frankie.mahalla.model.FCMNote
import uz.frankie.mahalla.model.Notification
import uz.frankie.mahalla.model.User
import uz.frankie.mahalla.utils.extentions.activityNavController
import uz.frankie.mahalla.utils.extentions.navigateSafely
import uz.frankie.mahalla.utils.logger.Logger
import uz.frankie.mahalla.viewmodels.NotificationVM

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val notifViewModel: NotificationVM by viewModels()
    private val binding by viewBinding(FragmentSignInBinding::bind)
    private val role = "hokim"
    private var token: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFCMToken()

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
//                binding.btnSignIn.isEnabled = binding.userName.text.toString().isNotEmpty() &&
//                        binding.passwordEt.text.toString().length > 8


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // not needed in this example
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // not needed in this example
            }
        }


        binding.apply {
            userName.addTextChangedListener(textWatcher)
            passwordEt.addTextChangedListener(textWatcher)

            btnSignIn.isEnabled = true
        }

        binding.btnSignIn.setOnClickListener {
            val user = User(id = 1, role = "hokim", name = "Azizjon", token)
            val message = FCMNote(
                Notification(
                    title = "Test",
                    body = "Message Test"
                ),
                arrayListOf("cGY5OQWIRnS_alPfqCgn7-:APA91bGj0RCGsHOQU18Sij_LPrirXpP55pBcbJ2k-ka1Fv1HFbGbJ_0Rmo0zhkPxLzW2QO9wDAodh424oFnzioztF_4dzB_1H_v9ovm5bnaHERioTWeQl1vJsFrJME221_HuVKszddvh")
            )

            when (role) {
                "hokim" -> {
                    activityNavController().navigateSafely(R.id.action_global_governorFlowFragment)
                }
                "rais" -> activityNavController().navigateSafely(R.id.action_global_villagesFlowFragment)
            }


            Toast.makeText(requireActivity(), "Signed in successfully", Toast.LENGTH_SHORT).show()
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
//        notifViewModel.uiState.collect(viewLifecycleOwner)
    }

}