package uz.frankie.mahalla.ui.villages.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentVilllagePassportBinding

class VillagePassportScreen:Fragment(R.layout.fragment_villlage_passport) {
    private val binding by viewBinding(FragmentVilllagePassportBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            llInformation.setOnClickListener {
                findNavController().navigate(R.id.action_passwordOfTownScreen_to_villageDetailsFragment)
            }
            llTownWorker.setOnClickListener {
                findNavController().navigate(R.id.action_passwordOfTownScreen_to_villageStaff)
            }
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }

            llInformationAll.setOnClickListener {
                findNavController().navigate(R.id.action_passwordOfTownScreen_to_villageAllInformationFragment)
            }
        }
    }
}