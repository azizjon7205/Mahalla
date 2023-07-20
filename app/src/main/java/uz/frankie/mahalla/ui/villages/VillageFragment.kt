package uz.frankie.mahalla.ui.villages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentVillageBinding
import uz.frankie.mahalla.model.Village
import uz.frankie.mahalla.adapter.village.VillagesWorkersAdapter
import uz.frankie.mahalla.utils.SharedPreferenceHelper
import javax.inject.Inject

@AndroidEntryPoint
class VillageFragment : Fragment(R.layout.fragment_village) {
    private val binding by viewBinding(FragmentVillageBinding::bind)
    private val adapterVillageWorkers by lazy { VillagesWorkersAdapter() }
    @Inject
    lateinit var preferences: SharedPreferenceHelper


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        adapterVillageWorkers.submitList(listOfWorkersVillages())
        adapterVillageWorkers.onClick = {
            findNavController().navigate(R.id.action_villagesFragment_to_governorAssistantFragment)
        }

        binding.apply {
            rvVillageWorkers.adapter = adapterVillageWorkers

            llDetail.setOnClickListener {
                findNavController().navigate(R.id.action_villagesFragment_to_passwordOfTownScreen)
            }
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }

            when(preferences.getRole()){
                "hokim" -> ivNotification.visibility = View.GONE
                "rais" -> {
                    ivNotification.visibility = View.VISIBLE
                    ivBack.visibility = View.GONE
                }

            }
        }
    }

    private fun listOfWorkersVillages(): List<Village>{
        var list = ArrayList<Village>()
        list.add(Village(1, "Mahalla raisi"))
        list.add(Village(2, "Xotin qizlar"))
        list.add(Village(3, "Yoshlar yetalchisi"))
        list.add(Village(4, "Hokim yordamchisi"))
        list.add(Village(5, "Mahalla noziri"))

        return list
    }

}