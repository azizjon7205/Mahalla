package uz.frankie.mahalla.ui.villages

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FlowFragmentVillagesBinding
import uz.frankie.mahalla.ui.BaseFlowFragment

class VillagesFlowFragment : BaseFlowFragment(R.layout.flow_fragment_villages, R.id.nav_host_fragment_village) {
    private val binding by viewBinding(FlowFragmentVillagesBinding::bind)

    override fun setupNavigation(navController: NavController) {
        super.setupNavigation(navController)

    }
}