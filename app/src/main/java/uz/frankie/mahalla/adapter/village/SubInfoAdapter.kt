package uz.frankie.mahalla.adapter.village

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.frankie.mahalla.databinding.ItemTwoInfoBinding
import uz.frankie.mahalla.model.village_all_info.TwoInfoDataModel

class SubInfoAdapter : ListAdapter<TwoInfoDataModel, SubInfoAdapter.VH>(ITEM_DIFF) {

    inner class VH(private val binding: ItemTwoInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TwoInfoDataModel) {
            binding.apply {
                nameTv.text = item.name
                countTv.text = item.amount
            }
        }
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<TwoInfoDataModel>() {
            override fun areItemsTheSame(
                oldItem: TwoInfoDataModel,
                newItem: TwoInfoDataModel
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: TwoInfoDataModel,
                newItem: TwoInfoDataModel
            ): Boolean = oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemTwoInfoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(currentList[position])

    override fun getItemCount(): Int = currentList.size

}