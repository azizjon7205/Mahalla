package uz.frankie.mahalla.ui.governor.task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.ScreenToTownBinding
import uz.frankie.mahalla.dialogs.governor.TownDialog
import uz.frankie.mahalla.dialogs.governor_assistant.CriteriaDialog
import java.util.*

class ToTownScreen : Fragment(R.layout.screen_to_town) {

    private val binding by viewBinding(ScreenToTownBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClick()
    }


    private fun initClick() {
        val mTimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)


        mTimePicker = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                binding.tvTime.text = String.format("%02d : %d", hourOfDay, minute)
            },
            hour, minute, false
        )

        binding.llTime.setOnClickListener {
            mTimePicker.show()
        }
        val mcurentTime = Calendar.getInstance()
        val year = mcurentTime.get(Calendar.YEAR)
        val month = mcurentTime.get(Calendar.MONTH)
        val day = mcurentTime.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            requireContext(),
            { view, year, month, dayOfMonth ->
                binding.tvDate.text = String.format(
                    "%02d - %02d - %04d",
                    dayOfMonth,
                    month + 1,
                    year
                )
            }, year, month, day
        )
        binding.llDate.setOnClickListener {
            datePicker.show()
        }

        binding.llTown.setOnClickListener {
            TownDialog(requireContext(), townList()).apply {
//                clickSelectCriteria { criteriaName ->
//                    personList(criteriaName)
//                }
            }.show()
        }
    }

    private fun townList(): ArrayList<String>{
        val items = ArrayList<String>()
        items.add("Shodlik mahallasi")
        items.add("Mustaqillik mahallasi")
        items.add("Shohona mahallasi")
        items.add("Do'stlik mahallasi")
        items.add("Tinchlik mahallasi")
        items.add("Obod mahallasi")
        items.add("Alisher Navoiy mahallasi")
        items.add("Hamza mahallasi")
        items.add("Shodlik mahallasi")
        items.add("Mustaqillik mahallasi")
        items.add("Shohona mahallasi")
        items.add("Do'stlik mahallasi")
        items.add("Tinchlik mahallasi")
        items.add("Obod mahallasi")
        items.add("Alisher Navoiy mahallasi")
        items.add("Hamza mahallasi")
        return items
    }
}