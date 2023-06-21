package uz.frankie.mahalla.ui.villages.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentVillageWorkersBinding

class VillageWorkersScreen: Fragment(R.layout.fragment_village_workers) {
    private val binding by viewBinding(FragmentVillageWorkersBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {

            mvRais.setOnClickListener {
                findNavController().navigate(R.id.action_villageStaff_to_staffInfo)
            }

            mvHokimYordamchisi.setOnClickListener {
                findNavController().navigate(R.id.action_villageStaff_to_staffInfo)
            }

            mvYoshlarYetakchisi.setOnClickListener {
                findNavController().navigate(R.id.action_villageStaff_to_staffInfo)
            }

            mvHuquqTargibot.setOnClickListener {
                findNavController().navigate(R.id.action_villageStaff_to_staffInfo)
            }

            mvXotinQizlarFaoli.setOnClickListener {
                findNavController().navigate(R.id.action_villageStaff_to_staffInfo)
            }


        }
    }
}