package uz.frankie.mahalla.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.messaging.FirebaseMessaging
import uz.frankie.mahalla.MainActivity
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentSignInBinding
import uz.frankie.mahalla.model.User
import uz.frankie.mahalla.utils.extentions.activityNavController
import uz.frankie.mahalla.utils.extentions.navigateSafely
import uz.frankie.mahalla.utils.logger.Logger


class SignInFragment : Fragment(R.layout.fragment_sign_in) {
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

            when(role){
                "hokim" -> {
                    activityNavController().navigateSafely(R.id.action_global_governorFlowFragment)
                }
                "rais" -> activityNavController().navigateSafely(R.id.action_global_villagesFlowFragment)
            }

            Toast.makeText(requireActivity(), "Signed in successfully", Toast.LENGTH_SHORT).show()
        }

    }

    private fun loadFCMToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful){
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


}