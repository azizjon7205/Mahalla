package uz.frankie.mahalla.utils

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import uz.frankie.mahalla.R
import uz.frankie.mahalla.data.MyDatabase
import uz.frankie.mahalla.data.entity.MyselfData
import java.util.*


class MyselfDialog : DialogFragment() {

    private lateinit var editText: EditText
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.add_info_to_myself, null)

        editText = view.findViewById(R.id.edt_write)
        textView1 = view.findViewById(R.id.tv_date)
        textView1.setOnClickListener { showDatePicker() }
        textView2 = view.findViewById(R.id.tv_time)
        textView2.setOnClickListener { showTimePicker() }

        builder.setView(view)
            .setTitle("My Dialog")
            .setPositiveButton("Save") { _, _ ->
//                saveDataToDatabase()
            }
            .setNegativeButton("Cancel", null)

        return builder.create()
    }


    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, yearSelected, monthSelected, daySelected ->
                val selectedDate = "${monthSelected + 1}/$daySelected/$yearSelected"
                textView1.text = selectedDate
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
            requireContext(),
            { _, hourOfDay, minute ->
                val selectedTime = "${hourOfDay.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}"
                textView2.text = selectedTime
            },
            hour,
            minute,
            false
        )

        timePickerDialog.show()
    }



    private suspend fun saveDataToDatabase() {
        val itemName = editText.text.toString()
        val date = textView1.text.toString()
        val time = textView2.text.toString()

        val item = MyselfData(text = itemName, date = date, time = time)
        val dao = MyDatabase.getInstance(requireContext()).itemDao()
        dao.insertItem(item)

        // update the RecyclerView with the new item
//        itemList.add(item)
//        recyclerViewAdapter.notifyItemInserted(itemList.size - 1)

        dismiss() // close the dialog
    }
}
