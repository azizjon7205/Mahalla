package uz.frankie.mahalla.ui.villages.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentVillageWorkersBinding
import uz.frankie.mahalla.model.Staff

class VillageStaff : Fragment(R.layout.fragment_village_workers) {

    private val binding by viewBinding(FragmentVillageWorkersBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initviews()
    }

    private fun initviews() {


        binding.apply {
            tvRais.setOnClickListener {
                findNavController().navigate(R.id.action_villagesFragment_to_governorAssistantFragment)
            }
        }

    }

}