package uz.frankie.mahalla.adapter.governor

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.frankie.mahalla.databinding.ItemTowntaskBinding
import uz.frankie.mahalla.model.Neighborhood


class TownTaskAdapter() : ListAdapter<Neighborhood, TownTaskAdapter.VH>(ITEM_DIFF) {

    private var itemClick: ((Neighborhood) -> Unit)? = null
    val selectedList: MutableList<Neighborhood> = mutableListOf()
    fun itemClicked(f: (item: Neighborhood) -> Unit) {
        itemClick = f
    }

    inner class VH(private val binding: ItemTowntaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Neighborhood){
            binding.tvTitle.text = item.name

            binding.checkman.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    selectedList.add(item)
                } else{
                    if (selectedList.contains(item)){
                        selectedList.remove(item)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemTowntaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    fun setData(items: List<Neighborhood>) {
        submitList(items)
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Neighborhood>() {
            override fun areItemsTheSame(oldItem: Neighborhood, newItem: Neighborhood): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Neighborhood, newItem: Neighborhood): Boolean {
                return oldItem == newItem
            }
        }
    }
}