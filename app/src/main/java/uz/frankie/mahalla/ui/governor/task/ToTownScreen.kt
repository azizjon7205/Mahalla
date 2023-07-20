package uz.frankie.mahalla.ui.governor.task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.ScreenToTownBinding
import uz.frankie.mahalla.dialogs.governor.TownDialog
import uz.frankie.mahalla.dialogs.governor_assistant.CriteriaDialog
import uz.frankie.mahalla.model.FCMNote
import uz.frankie.mahalla.model.Neighborhood
import uz.frankie.mahalla.model.Notification
import uz.frankie.mahalla.ui.BaseFragment
import uz.frankie.mahalla.utils.Constants.FCM_API_KEY
import uz.frankie.mahalla.utils.extentions.collectLA
import uz.frankie.mahalla.viewmodels.NeighborhoodVM
import uz.frankie.mahalla.viewmodels.NotificationVM
import java.util.*

@AndroidEntryPoint
class ToTownScreen constructor(val navControllerTask: NavController) : BaseFragment<ScreenToTownBinding>(ScreenToTownBinding::inflate) {

    private var townsList: List<Neighborhood> = mutableListOf()
    private var selectedVillages: MutableList<Neighborhood> = arrayListOf()

    private val notificationVM: NotificationVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectUiState()
        initClick()
    }

    override fun onViewCreate() {

    }

    override fun getToolbar(): Toolbar? {
        return null
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
            TownDialog(requireContext(), townsList).apply {
                this.selectCriteria = {
                    selectedVillages.clear()
                    selectedVillages.addAll(it)
                    this@ToTownScreen.binding.tvTowEnter.text = "${selectedVillages.size} ta mahalla belgilandi"
                }
            }.show()
        }

        binding.bSend.setOnClickListener {
            val body: String = binding.etDescription.text.toString().plus("\nSana ${binding.tvDate.text}, ${binding.tvTime.text}")
            val title = "Navbatdagi xabarnoma"

            val fcmNote = FCMNote(
                api_key = FCM_API_KEY,
                notif_body = getNeighborhoodsToSendNotif(),
                body = body,
                title = title
            )
            Log.d("Notification >>> ", "$fcmNote")

            notificationVM.sendMessage(fcmNote)

        }


    }
    fun getNeighborhoodsToSendNotif(): List<Notification>{
        val list = mutableListOf<Notification>()
        selectedVillages.forEach {
            list.add(Notification(fcm_token = it.fcm_token, neighborhood_id = it.id))
        }
        return  list
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

    private fun collectUiState() {
        neighborhoodVM.uiState.collectLA(viewLifecycleOwner) { uiState ->
            if (uiState.data.isNotEmpty()) {
                // set data Adapter or UI
                townsList = uiState.data
                Log.d("Task >>> ", "Villages list for task: ${townsList}")

            }

            if (uiState.isLoading) {
                val loaderDialog = true  // loaderDialog show
            } else {
                val loaderDialog = false  // loaderDialog dismiss
            }

            Log.d("@@@", "Villages list: ${uiState.errorMessage}")
        }

        notificationVM.uiState.collectLA(viewLifecycleOwner){ uiState ->
            if (!uiState.notification.error){
                Toast.makeText(
                    requireContext(),
                    "Xabar muvaffaqqiyatly yuborildi",
                    Toast.LENGTH_SHORT
                ).show()

                navControllerTask.navigateUp()
            }
            if (uiState.isLoading){
                binding.bSend.text = ""
                binding.flLoading.visibility = View.VISIBLE
            } else{
                binding.bSend.text = "Yuborish"
                binding.flLoading.visibility = View.GONE

            }

        }
    }
}