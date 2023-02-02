package uz.frankie.mahalla.ui.governor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentGovernorBinding
import uz.frankie.mahalla.model.Village
import uz.frankie.mahalla.ui.adapter.governor.VillagesListAdapter

class GovernorFragment : Fragment(R.layout.fragment_governor) {
    private val binding by viewBinding(FragmentGovernorBinding::bind)
    private val adapterVillages by lazy { VillagesListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        adapterVillages.submitList(listOfVillages())
        adapterVillages.onClick = {
//            findNavController().navigate()
        }

        binding.apply {
            rvVillages.adapter = adapterVillages
        }
    }

    private fun listOfVillages(): List<Village>{
        var list = ArrayList<Village>()
        list.add(Village(1, "Shodlik"))
        list.add(Village(2, "Shodlik"))
        list.add(Village(3, "Shodlik"))
        list.add(Village(4, "Shodlik"))
        list.add(Village(5, "Shodlik"))

        return list
    }
}