package uz.frankie.mahalla.adapter.governor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.frankie.mahalla.databinding.ItemVellagesBinding
import uz.frankie.mahalla.model.Neighborhood
import java.util.*

class VillagesListAdapter: ListAdapter<Neighborhood, VillagesListAdapter.ViewHolder>(ITEM_DIFF) {
    var onClick: ((Neighborhood) -> Unit)? = null
    var list = listOf<Neighborhood>()

    fun submit(items: List<Neighborhood>){
        list = items
        submitList(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemVellagesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemVellagesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind() {
            val village = getItem(adapterPosition)
            with(binding) {
                tvMahalla.text = village.name
                root.setOnClickListener {
                    onClick?.invoke(village)
                }
            }
        }
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Neighborhood>() {
            override fun areItemsTheSame(oldItem: Neighborhood, newItem: Neighborhood): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Neighborhood, newItem: Neighborhood): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    fun performFiltering(constraint: CharSequence?) {
        val filteredList = mutableListOf<Neighborhood>()

        if (constraint.isNullOrEmpty()) {
            filteredList.addAll(list)
        } else {
            val query = constraint.toString().toLowerCase(Locale.getDefault())
            for (item in list) {
                if (item.name.toLowerCase(Locale.getDefault()).contains(query)) {
                    filteredList.add(item)
                }
            }
        }

        submitList(filteredList)
    }

    val filter = object: Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Neighborhood>()

            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(list)
            } else {
                val query = constraint.toString().toLowerCase(Locale.getDefault())
                for (item in list) {
                    if (item.name.toLowerCase(Locale.getDefault()).contains(query)) {
                        filteredList.add(item)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

            submitList(results?.values as MutableList<Neighborhood>)
        }

    }

}