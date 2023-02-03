package uz.frankie.mahalla.ui.governor_assistant

import androidx.navigation.NavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FlowFragmentGovernorAssistantBinding
import uz.frankie.mahalla.ui.BaseFlowFragment

class GovernorAssistantFlowFragment : BaseFlowFragment(R.layout.flow_fragment_governor_assistant, R.id.nav_host_fragment_governor_assistant) {
    private val binding by viewBinding(FlowFragmentGovernorAssistantBinding::bind)

    override fun setupNavigation(navController: NavController) {
        super.setupNavigation(navController)

    }
}