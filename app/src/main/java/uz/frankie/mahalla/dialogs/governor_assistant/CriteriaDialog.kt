package uz.frankie.mahalla.dialogs.governor_assistant

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import uz.frankie.mahalla.databinding.DialogCriteriaBinding
import uz.frankie.mahalla.adapter.governor_assistant.CriteriaAdapter

class CriteriaDialog(context: Context, criteriaList: List<String>) : AlertDialog(context) {
    val binding = DialogCriteriaBinding.inflate(LayoutInflater.from(context))
    private val criteriaAdapter by lazy { CriteriaAdapter() }

    private var selectCriteria: ((String) -> Unit)? = null
    fun clickSelectCriteria(f: (item: String) -> Unit) {
        selectCriteria = f
    }
    init {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        setView(binding.root)

        criteriaAdapter.apply {
            setData(criteriaList)
            itemClicked { criteriaName ->
                selectCriteria!!.invoke(criteriaName)
                dismiss()
            }
        }
        binding.rvCriteria.adapter = criteriaAdapter
    }

}