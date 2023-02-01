package uz.frankie.mahalla.ui.governor

import androidx.navigation.NavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FlowFragmentGovernorBinding
import uz.frankie.mahalla.ui.BaseFlowFragment

class GovernorFlowFragment : BaseFlowFragment(R.layout.flow_fragment_governor, R.id.nav_host_fragment_governor) {
    private val binding by viewBinding(FlowFragmentGovernorBinding::bind)

    override fun setupNavigation(navController: NavController) {
        super.setupNavigation(navController)

    }
}