package uz.frankie.mahalla.dialogs.governor

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import uz.frankie.mahalla.adapter.governor.TownTaskAdapter
import uz.frankie.mahalla.databinding.DialogTownBinding

class TownDialog(context: Context, townList: List<String>) : AlertDialog(context) {
    val binding = DialogTownBinding.inflate(LayoutInflater.from(context))
    private val townAdapter by lazy { TownTaskAdapter() }

    private var selectCriteria: ((String) -> Unit)? = null
//    fun clickSelectCriteria(f: (item: String) -> Unit) {
//        selectCriteria = f
//    }
    init {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        setView(binding.root)

        townAdapter.apply {
            setData(townList)
            itemClicked { townName ->
                selectCriteria!!.invoke(townName)
                dismiss()
            }
        }
        binding.rvCriteria.adapter = townAdapter
    }

}