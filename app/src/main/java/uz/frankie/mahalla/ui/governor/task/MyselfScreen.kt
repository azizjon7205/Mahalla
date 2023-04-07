package uz.frankie.mahalla.ui.governor.task

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.adapter.governor.MyselfAdapter
import uz.frankie.mahalla.data.entity.MyselfData
import uz.frankie.mahalla.databinding.ScreenMyselfBinding
import java.util.*

class MyselfScreen : Fragment(R.layout.screen_myself) {

    private lateinit var recyclerMyself: RecyclerView
    private lateinit var myselfAdapter: MyselfAdapter
    private lateinit var itemList: MutableList<MyselfData> // replace Item with your data class
    private val binding by viewBinding(ScreenMyselfBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            recyclerMyself = rvMyself
            recyclerMyself.layoutManager=LinearLayoutManager(requireContext())
            itemList = mutableListOf()
            myselfAdapter =MyselfAdapter(itemList)
            recyclerMyself.adapter=myselfAdapter
            floatingBtn.setOnClickListener {
                tvInfo.visibility = View.GONE
                ivEmpty.visibility = View.GONE
                rvMyself.visibility = View.VISIBLE
            }
        }
    }
}