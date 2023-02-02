package uz.frankie.mahalla.ui.governor_assistant.details

import uz.frankie.mahalla.databinding.FragmentDetailsBinding
import uz.frankie.mahalla.ui.governor_assistant.BaseGovernorAssistantFragment

class DetailsFragment : BaseGovernorAssistantFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    override fun onViewCreate() {
        binding.ivBack.setOnClickListener {
            navController.popBackStack()
        }
    }

}