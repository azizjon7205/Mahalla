package uz.frankie.mahalla.ui.villages.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentVillageDetailsBinding

class VillageDetailsFragment : Fragment(R.layout.fragment_village_details) {
    private val binding by viewBinding(FragmentVillageDetailsBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initviews()


    }

    private fun initviews() {
        binding.apply {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}