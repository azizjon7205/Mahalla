package uz.frankie.mahalla.adapter.governor_assistant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.frankie.mahalla.databinding.ItemPersonInfoBinding

class PersonInfoAdapter() : ListAdapter<PersonInfo, PersonInfoAdapter.VH>(ITEM_DIFF) {

    private var rootClick: ((PersonInfo) -> Unit)? = null
    fun setRootClickListener(f: (item: PersonInfo) -> Unit) {
        rootClick = f
    }

    inner class VH(private val binding: ItemPersonInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PersonInfo){
            binding.apply {
                tvFullName.text = item.fullName
                tvAddress.text = item.address
                tvAge.text = item.age.toString()
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