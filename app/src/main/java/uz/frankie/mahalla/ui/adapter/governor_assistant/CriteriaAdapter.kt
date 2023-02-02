package uz.frankie.mahalla.ui.adapter.governor_assistant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.frankie.mahalla.databinding.ItemCriteriaBinding

class CriteriaAdapter() : ListAdapter<String, CriteriaAdapter.VH>(ITEM_DIFF) {

    private var itemClick: ((String) -> Unit)? = null
    fun itemClicked(f: (item: String) -> Unit) {
        itemClick = f
    }

    inner class VH(private val binding: ItemCriteriaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String){
            binding.tvTitle.text = item
            binding.root.setOnClickListener {
                itemClick!!.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemCriteriaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    fun setData(items: List<String>) {
        submitList(items)
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

}
