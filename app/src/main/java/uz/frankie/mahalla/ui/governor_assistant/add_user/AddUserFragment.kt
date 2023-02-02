package uz.frankie.mahalla.ui.governor_assistant.add_user

import uz.frankie.mahalla.databinding.FragmentAddUserBinding
import uz.frankie.mahalla.ui.governor_assistant.BaseGovernorAssistantFragment


class AddUserFragment : BaseGovernorAssistantFragment<FragmentAddUserBinding>(FragmentAddUserBinding::inflate) {

    override fun onViewCreate() {
        binding.ivBack.setOnClickListener {
            navController.popBackStack()
        }
    }

}