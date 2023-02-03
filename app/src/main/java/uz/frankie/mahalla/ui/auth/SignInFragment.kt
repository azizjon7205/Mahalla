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
import uz.frankie.mahalla.MainActivity
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentSignInBinding


class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val binding by viewBinding(FragmentSignInBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.btnSignIn.isEnabled = binding.userName.text.toString().isNotEmpty() &&
                        binding.passwordEt.text.toString().length > 8


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
        }



            binding.btnSignIn.setOnClickListener {
               findNavController().navigate(R.id.action_global_governorFlowFragment)
                Toast.makeText(requireActivity(), "Signed in successfully", Toast.LENGTH_SHORT).show()
            }

    }


}