package uz.frankie.mahalla.ui.governor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentGovernorBinding
import uz.frankie.mahalla.model.Village
import uz.frankie.mahalla.adapter.governor.VillagesListAdapter
import uz.frankie.mahalla.utils.extentions.collectLA
import uz.frankie.mahalla.viewmodels.NeighborhoodVM

@AndroidEntryPoint
class GovernorFragment : Fragment(R.layout.fragment_governor) {
    private val binding by viewBinding(FragmentGovernorBinding::bind)
    private val adapterVillages by lazy { VillagesListAdapter() }
    private val neighborhoodVM: NeighborhoodVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        adapterVillages.submitList(listOfVillages())
        adapterVillages.onClick = {
            findNavController().navigate(R.id.action_governorFragment_to_villagesFragment)
        }

        binding.apply {
            rvVillages.adapter = adapterVillages
        }

        neighborhoodVM.getNeighborhoodList()
        collectUiState()
    }

    private fun collectUiState() {
        neighborhoodVM.uiState.collectLA(viewLifecycleOwner) { uiState ->
            if (uiState.neighborhoodList.isNotEmpty()) {
                // set data Adapter or UI
                val list = uiState.neighborhoodList
            }

            if (uiState.isLoading) {
                val loaderDialog = true  // loaderDialog show
            } else {
                val loaderDialog = false  // loaderDialog dismiss
            }
        }
    }

    private fun listOfVillages(): List<Village> {
        var list = ArrayList<Village>()
        list.add(Village(1, "Shodlik"))
        list.add(Village(2, "Shodlik"))
        list.add(Village(3, "Shodlik"))
        list.add(Village(4, "Shodlik"))
        list.add(Village(5, "Shodlik"))

        return list
    }
}