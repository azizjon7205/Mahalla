package uz.frankie.mahalla.dialogs.governor

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import uz.frankie.mahalla.adapter.governor.TownTaskAdapter
import uz.frankie.mahalla.databinding.DialogTownBinding
import uz.frankie.mahalla.model.Neighborhood

class TownDialog(context: Context, townList: List<Neighborhood>) : AlertDialog(context) {
    val binding = DialogTownBinding.inflate(LayoutInflater.from(context))
    private val townAdapter by lazy { TownTaskAdapter() }

    var selectCriteria: ((List<Neighborhood>) -> Unit)? = null
    val onClicked: ((List<Neighborhood>) -> Unit)? = null

    init {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        setView(binding.root)

        townAdapter.apply {
            setData(townList)
        }
        binding.rvCriteria.adapter = townAdapter
        binding.bContinue.setOnClickListener {
            if (binding.checkman.isChecked){
                selectCriteria?.invoke(townList)
            } else{
                selectCriteria?.invoke(townAdapter.selectedList)
            }

            dismiss()
        }
    }

}