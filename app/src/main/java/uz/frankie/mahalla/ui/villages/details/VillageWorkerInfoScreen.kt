package uz.frankie.mahalla.ui.villages.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentVillageWorkerInfoBinding
import uz.frankie.mahalla.model.Staff

class VillageWorkerInfoScreen: Fragment(R.layout.fragment_village_worker_info) {
    private val binding by viewBinding(FragmentVillageWorkerInfoBinding::bind)

    private val args: VillageWorkerInfoScreenArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviews()
    }

    private fun initviews() {
        val staff: Staff = args.staffData
        binding.apply {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            titleStaffInfo.text = staff.infoDuty
            nameStaffInfo.text = staff.fullname
            dutyStaffInfo.text = staff.info
            tvDateOfBirth.text = staff.dateOfBirth
            tvPlaceOfBirth.text = staff.province
            tvNationality.text = staff.nationality
            tvDegree.text = staff.degree
            tvFieldOfStudy.text = staff.major
            tvAddress.text = staff.address
            tvPhoneNumber.text = staff.phonenNumber
            tvFamilyInfo.text = staff.isMarried
            ivStaff.setImageResource(staff.image)
            tvDownload.setOnClickListener {
                Toast.makeText(context, "Tez kunda!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}