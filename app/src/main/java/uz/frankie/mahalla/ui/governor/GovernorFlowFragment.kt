package uz.frankie.mahalla.ui.governor

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FlowFragmentGovernorBinding
import uz.frankie.mahalla.ui.BaseFlowFragment
import uz.frankie.mahalla.utils.SharedPreferenceHelper
import javax.inject.Inject

class GovernorFlowFragment : BaseFlowFragment(R.layout.flow_fragment_governor, R.id.nav_host_fragment_governor) {
    private val binding by viewBinding(FlowFragmentGovernorBinding::bind)
    @Inject
    lateinit var preferences: SharedPreferenceHelper

    override fun setupNavigation(navController: NavController) {
        super.setupNavigation(navController)

        binding.bnvGovernor.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.governorFragment, R.id.taskFragment, R.id.profileFragment, R.id.settingsFragment -> {
                    binding.bnvGovernor.visibility = View.VISIBLE
                }

                else -> {
                    binding.bnvGovernor.visibility = View.GONE
                }
            }
        }

    }
}