package uz.frankie.mahalla.ui.adapter.governor_assistant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.frankie.mahalla.databinding.ItemPersonInfoBinding

class PersonInfoAdapter() : ListAdapter<PersonInfo, PersonInfoAdapter.VH>(ITEM_DIFF) {

    private var exchangeIconClick: ((PersonInfo) -> Unit)? = null
    fun setExchangeIconClickListener(f: (item: PersonInfo) -> Unit) {
        exchangeIconClick = f
    }

    private var deleteIconClick: ((String) -> Unit)? = null
    fun setDeleteIconClickListener(f: (ccy: String) -> Unit) {
        deleteIconClick = f
    }

    inner class VH(private val binding: ItemPersonInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PersonInfo){
            binding.tvFullName.text = item.fullName
            binding.tvAddress.text = item.address
            binding.tvAge.text = item.age.toString()
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

    fun setData(items: List<PersonInfo>) {
        submitList(items)
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<PersonInfo>() {
            override fun areItemsTheSame(oldItem: PersonInfo, newItem: PersonInfo): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: PersonInfo, newItem: PersonInfo): Boolean {
                return oldItem == newItem
            }
        }
    }

}

data class PersonInfo(
    val fullName: String,
    val address: String,
    val age: Int
)