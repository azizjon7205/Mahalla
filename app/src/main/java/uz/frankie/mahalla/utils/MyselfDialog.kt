package uz.frankie.mahalla.utils

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import uz.frankie.mahalla.data.entity.MyselfData
import uz.frankie.mahalla.databinding.DialogAddTaskToMyselfBinding
import java.util.Calendar

class MyselfDialog(context: Context, private val item: MyselfData? = null) : AlertDialog(context) {

    private val binding = DialogAddTaskToMyselfBinding.inflate(LayoutInflater.from(context))

    private var saveTask: ((MyselfData) -> Unit)? = null
    fun setOnSaveTaskListener(f: (item: MyselfData) -> Unit) {
        saveTask = f
    }

    init {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        setView(binding.root)

        binding.apply {
            if (item != null) {
                // Editing an existing item
                edtWrite.setText(item.text)
                tvDate.text = item.date
                tvTime.text = item.time
            } else {
                edtWrite.setText("")
                tvDate.text = ""
                tvTime.text = ""
                llDate.setOnClickListener { showDatePicker() }
                llTime.setOnClickListener { showTimePicker() }
                btnSave.setOnClickListener {
                    saveTask?.invoke(
                        MyselfData(
                            text = edtWrite.text.toString(),
                            date = tvDate.text.toString(),
                            time = tvTime.text.toString()
                        )
                    )
                    dismiss()
                }
            }

            llDate.setOnClickListener { showDatePicker() }
            llTime.setOnClickListener { showTimePicker() }
            btnSave.setOnClickListener {
                val updatedItem = MyselfData(
                    id = item?.id ?: 0,
                    text = edtWrite.text.toString(),
                    date = tvDate.text.toString(),
                    time = tvTime.text.toString()
                )
                saveTask?.invoke(updatedItem)
                dismiss()
            }
        }

        val windowLayoutParams = WindowManager.LayoutParams().apply {
            copyFrom(window?.attributes)
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
        }
        window?.attributes = windowLayoutParams
    }
    override fun onBackPressed() {
        super.onBackPressed()
        dismiss()
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

//class MyselfDialog(context: Context) : AlertDialog(context) {
//
//    val binding = DialogAddTaskToMyselfBinding.inflate(LayoutInflater.from(context),null,false)
//
//    private var createTask: ((MyselfData) -> Unit)? = null
//    fun setOnCreateTaskListener(f: (item: MyselfData) -> Unit) {
//        createTask = f
//    }
//
//    init {
//        window?.setBackgroundDrawableResource(android.R.color.transparent)
//        setCancelable(false)
//
//        val windowLayoutParams = WindowManager.LayoutParams().apply {
//            copyFrom(window?.attributes)
//            width = WindowManager.LayoutParams.MATCH_PARENT
//            height = WindowManager.LayoutParams.WRAP_CONTENT
//        }
//        window?.attributes = windowLayoutParams
//
//        binding.apply {
//            llDate.setOnClickListener { showDatePicker() }
//            llTime.setOnClickListener { showTimePicker() }
//            btnSave.setOnClickListener {
//                createTask?.invoke(
//                    MyselfData(
//                        text = edtWrite.text.toString(),
//                        date = tvDate.text.toString(),
//                        time = tvTime.text.toString()
//                    )
//
//                )
//
//                dismiss()
//            }
//        }
//
//        setView(binding.root)
//    }
//

//
//}
