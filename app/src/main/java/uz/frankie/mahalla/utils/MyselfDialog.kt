package uz.frankie.mahalla.utils

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import uz.frankie.mahalla.data.entity.MyselfData
import uz.frankie.mahalla.databinding.DialogAddTaskToMyselfBinding
import java.util.*

class MyselfDialog(context: Context) : AlertDialog(context) {

    val binding = DialogAddTaskToMyselfBinding.inflate(LayoutInflater.from(context))

    private var createTask: ((MyselfData) -> Unit)? = null
    fun setOnCreateTaskListener(f: (item: MyselfData) -> Unit) {
        createTask = f
    }

    init {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)

        binding.apply {
            llDate.setOnClickListener { showDatePicker() }
            llTime.setOnClickListener { showTimePicker() }
            btnSave.setOnClickListener {
                createTask?.invoke(
                    MyselfData(
                        text = edtWrite.text.toString(),
                        date = tvDate.text.toString(),
                        time = tvTime.text.toString()
                    )
                )
                dismiss()
            }
        }

        setView(binding.root)
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            binding.root.context,
            { _, yearSelected, monthSelected, daySelected ->
                val selectedDate = "${monthSelected + 1}/$daySelected/$yearSelected"
                binding.tvDate.text = selectedDate
            },
            year,
            month,
            day
        ) // add this closing parenthesis here

        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            binding.root.context,
            { _, hourOfDay, minute ->
                val selectedTime =
                    "${hourOfDay.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}"
                binding.tvTime.text = selectedTime
            },
            hour,
            minute,
            false
        )

        timePickerDialog.show()
    }

}
