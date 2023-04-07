package uz.frankie.mahalla.ui.governor.task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.data.entity.MyselfData
import uz.frankie.mahalla.databinding.AddInfoToMyselfBinding
import uz.frankie.mahalla.utils.MySelfViewModel
import java.util.*

class AddInfoToMyselfScreen : Fragment(R.layout.add_info_to_myself) {

    private val binding by viewBinding(AddInfoToMyselfBinding::bind)
    private val viewModel: MySelfViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()


    }

    private fun initView() {
        val info = binding.edtWrite.text.toString()
        val date = binding.tvDate.text.toString()
        val time = binding.tvTime.text.toString()


        val timePickerDialog = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
            val time = "$hourOfDay:$minute"
            binding.tvTime.text = time
        }, 0, 0, true)

        binding.llTime.setOnClickListener {
            timePickerDialog.show()
        }
        binding.tvDate.setOnClickListener {
            showDatePicker()
        }
        val myData = MyselfData(text = info, date = date, time = time)
        viewModel.insertMyData(myData)
    }

    private fun showDatePicker() {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
            val date = "${dayOfMonth}/${month + 1}/${year}"
            binding.tvDate.text = date
        }, year, month, day)

        datePicker.show()
    }


}