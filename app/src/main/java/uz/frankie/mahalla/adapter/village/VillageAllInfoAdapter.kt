package uz.frankie.mahalla.adapter.village

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.frankie.mahalla.databinding.*
import uz.frankie.mahalla.model.village_all_info.AllInfoDataModel

class VillageAllInfoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<AllInfoDataModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newIcons: List<AllInfoDataModel>) {
        items.clear()
        items.addAll(newIcons)
        notifyDataSetChanged()
    }

    inner class TitleVH(private val binding: ItemTitleInfoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: AllInfoDataModel){
            binding.titleTv.text = item.title
        }
    }

    inner class TwoInfoVH(private val binding: ItemTwoInfoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: AllInfoDataModel){
            binding.apply {
                nameTv.text = item.title
                countTv.text = item.infoOne
            }
        }
    }

    inner class TwoInfoGreenVH(private val binding: ItemTwoGreenInfoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: AllInfoDataModel){
            binding.apply {
                nameTv.text = item.title
                countTv.text = item.infoOne
            }
        }
    }

    inner class TwoInfoFirstBoldVH(private val binding: ItemTwoFirstBoldInfoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: AllInfoDataModel){
            binding.apply {
                nameTv.text = item.title
                countTv.text = item.infoOne
            }
        }
    }

    inner class TwoInfoFirstBoldHeaderVH(private val binding: ItemTwoFirstBoldHeaderInfoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: AllInfoDataModel){
            binding.apply {
                nameTv.text = item.title
                countTv.text = item.infoOne
            }
        }
    }

    inner class TwoSubInfoVH(private val binding: ItemSubTwoInfoBinding): RecyclerView.ViewHolder(binding.root){
        private val subAdapter by lazy { SubInfoAdapter() }
        fun bind(item: AllInfoDataModel){
            subAdapter.submitList(item.subInfo)
            binding.rvSubInfo.adapter = subAdapter
            binding.titleTv.isSelected = true
        }
    }

    inner class ThreeInfoVH(private val binding: ItemThreeInfoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: AllInfoDataModel){
            binding.apply {
                nameTv.text = item.title
                amountOne.text = item.infoOne
                amountTwo.text = item.infoTwo
            }
        }
    }

    inner class ThreeYellowInfoVH(private val binding: ItemThreeYellowInfoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: AllInfoDataModel){
            binding.apply {
                nameTv.text = item.title
                amountOne.text = item.infoOne
                amountTwo.text = item.infoTwo
            }
        }
    }

    inner class ThreeFirstBoldInfoVH(private val binding: ItemThreeFirstBoldInfoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: AllInfoDataModel){
            binding.apply {
                nameTv.text = item.title
                amountOne.text = item.infoOne
                amountTwo.text = item.infoTwo
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position].itemType){
            AllInfoType.TITLE -> 1
            AllInfoType.TWO_INFO -> 2
            AllInfoType.TWO_INFO_GREEN -> 3
            AllInfoType.TWO_INFO_FIRST_BOLD -> 4
            AllInfoType.TWO_INFO_FIRST_BOLD_HEADER -> 9
            AllInfoType.SUB_TWO_INFO -> 5
            AllInfoType.THREE_INFO -> 6
            AllInfoType.THREE_INFO_YELLOW -> 7
            AllInfoType.THREE_INFO_FIRST_BOLD -> 8
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1 ->{ TitleVH(ItemTitleInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)) }
            2 ->{ TwoInfoVH(ItemTwoInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)) }
            3 ->{ TwoInfoGreenVH(ItemTwoGreenInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)) }
            4 ->{ TwoInfoFirstBoldVH(ItemTwoFirstBoldInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)) }
            9 ->{ TwoInfoFirstBoldHeaderVH(ItemTwoFirstBoldHeaderInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)) }
            5 ->{ TwoSubInfoVH(ItemSubTwoInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)) }
            6 ->{ ThreeInfoVH(ItemThreeInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)) }
            7 ->{ ThreeYellowInfoVH(ItemThreeYellowInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)) }
            else -> { ThreeFirstBoldInfoVH(ItemThreeFirstBoldInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)) }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TitleVH -> holder.bind(items[position])
            is TwoInfoVH -> holder.bind(items[position])
            is TwoInfoGreenVH -> holder.bind(items[position])
            is TwoInfoFirstBoldVH -> holder.bind(items[position])
            is TwoInfoFirstBoldHeaderVH -> holder.bind(items[position])
            is TwoSubInfoVH -> holder.bind(items[position])
            is ThreeInfoVH -> holder.bind(items[position])
            is ThreeYellowInfoVH -> holder.bind(items[position])
            is ThreeFirstBoldInfoVH -> holder.bind(items[position])
        }
    }

}




enum class AllInfoType(data: String){
    TITLE(data = "info_title"),
    TWO_INFO(data = "two_info"),
    TWO_INFO_GREEN(data = "two_info_green"),
    TWO_INFO_FIRST_BOLD(data = "two_info_first_bold"),
    TWO_INFO_FIRST_BOLD_HEADER(data = "two_info_first_bold_header"),
    SUB_TWO_INFO(data = "sub_two_info"),
    THREE_INFO(data = "three_info"),
    THREE_INFO_YELLOW(data = "three_info_yellow"),
    THREE_INFO_FIRST_BOLD(data = "three_info_first_bold")
}

