package uz.frankie.mahalla.adapter.governor_assistant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.frankie.mahalla.databinding.ItemPersonInfoBinding
import uz.frankie.mahalla.network.models.population.response.PopulationDataModel
import java.util.Calendar

class PersonInfoAdapter() : ListAdapter<PopulationDataModel, PersonInfoAdapter.VH>(ITEM_DIFF) {

    private var rootClick: ((PopulationDataModel) -> Unit)? = null
    fun setRootClickListener(f: (item: PopulationDataModel) -> Unit) {
        rootClick = f
    }

    inner class VH(private val binding: ItemPersonInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PopulationDataModel) {
            val calendar = Calendar.getInstance()
            val currentYear = calendar.get(Calendar.YEAR)
            binding.apply {
                tvFullName.text = item.full_name
                tvAddress.text = item.permanent_address
                tvAge.text = if (item.birthday.length > 4) (currentYear - item.birthday.substring(0,4).toInt()).toString() else ""
                root.setOnClickListener {
                    rootClick!!.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemPersonInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    fun setData(items: List<PopulationDataModel>) {
        submitList(items)
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<PopulationDataModel>() {
            override fun areItemsTheSame(
                oldItem: PopulationDataModel,
                newItem: PopulationDataModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PopulationDataModel,
                newItem: PopulationDataModel
            ): Boolean {
                return oldItem.full_name == newItem.full_name
                        && oldItem.birthday == newItem.birthday
                        && oldItem.passport == newItem.passport
            }
        }
    }

}