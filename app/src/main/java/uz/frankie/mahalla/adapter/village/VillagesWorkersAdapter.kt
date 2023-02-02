package uz.frankie.mahalla.adapter.village

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.frankie.mahalla.databinding.ItemVellagesBinding
import uz.frankie.mahalla.model.Village

class VillagesWorkersAdapter: ListAdapter<Village, VillagesWorkersAdapter.ViewHolder>(ITEM_DIFF) {
    var onClick: ((Village) -> Unit)? = null

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
                root.setOnClickListener {
                    onClick?.invoke(village)
                }
            }
        }
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Village>() {
            override fun areItemsTheSame(oldItem: Village, newItem: Village): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Village, newItem: Village): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

}