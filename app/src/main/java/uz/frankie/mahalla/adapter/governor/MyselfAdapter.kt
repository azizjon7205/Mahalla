package uz.frankie.mahalla.adapter.governor

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.frankie.mahalla.R
import uz.frankie.mahalla.data.entity.MyselfData
import uz.frankie.mahalla.model.Myself
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MyselfAdapter : RecyclerView.Adapter<MyselfAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<MyselfData>() {
        override fun areItemsTheSame(oldItem: MyselfData, newItem: MyselfData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyselfData, newItem: MyselfData): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    private var itemClick: ((MyselfData, View) -> Unit)? = null
    fun itemClicked(f: (item: MyselfData, view: View) -> Unit) {
        itemClick = f
    }

    private var optionsClick: ((MyselfData) -> Unit)? = null
    fun optionsClicked(f: (item: MyselfData) -> Unit) {
        optionsClick = f
    }

    private var deleteClick: ((MyselfData) -> Unit)? = null
    fun deleteClicked(f: (item: MyselfData) -> Unit) {
        deleteClick = f
    }

    fun submitData(list: List<MyselfData>) {
        val sortedList = list.sortedWith(compareBy<MyselfData> { convertToDate(it.date) }
            .thenBy { convertToTime(it.time) })

        differ.submitList(sortedList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_my_self, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_information)
        private val dateTextView: TextView = itemView.findViewById(R.id.tv_date)
        private val timeTextView: TextView = itemView.findViewById(R.id.tv_time)

        init {
            itemView.setOnClickListener {
                val item = differ.currentList[adapterPosition]
                itemClick?.invoke(item, itemView)
            }
        }

        fun bind(item: MyselfData) {
            nameTextView.text = item.text
            dateTextView.text = item.date
            timeTextView.text = item.time
        }
    }

    private fun convertToDate(dateString: String): Date {
        val format = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return format.parse(dateString) ?: Date()
    }

    private fun convertToTime(timeString: String): Date {
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        return format.parse(timeString) ?: Date()
    }

    fun showPopupMenu(context: Context, view: View, item: MyselfData) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.myself_pop_up_menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit_item -> {
                    optionsClick?.invoke(item)
                    true
                }
                R.id.delete_item -> {
                    deleteClick?.invoke(item)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}
//class MyselfAdapter() :
//    RecyclerView.Adapter<MyselfAdapter.ViewHolder>() {
//    private val dif = AsyncListDiffer(this, ITEM_DIF)
//
//    private var itemClick: ((MyselfData) -> Unit)? = null
//    fun itemClicked(f: (item: MyselfData) -> Unit) {
//        itemClick = f
//    }
//
//    fun submitData(list: List<MyselfData>) {
//        val sortedList = list.sortedWith(compareBy<MyselfData> { convertToDate(it.date) }
//            .thenBy { convertToTime(it.time) })
//
//        dif.submitList(sortedList)
//        Log.d("DIFLIST", "submitData: ${list}")
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_my_self, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = dif.currentList[position]
//        holder.nameTextView.text = item.text
//        holder.dateTextView.text = item.date
//        holder.timeTextView.text = item.time
//    }
//
//    override fun getItemCount(): Int {
//        return dif.currentList.size
//    }
//
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val nameTextView: TextView = itemView.findViewById(R.id.tv_information)
//        val dateTextView: TextView = itemView.findViewById(R.id.tv_date)
//        val timeTextView: TextView = itemView.findViewById(R.id.tv_time)
//
//    }
//
//
//
//    companion object {
//        private val ITEM_DIF = object : DiffUtil.ItemCallback<MyselfData>() {
//
//            override fun areItemsTheSame(oldItem: MyselfData, newItem: MyselfData): Boolean =
//                oldItem.id == newItem.id
//
//            override fun areContentsTheSame(
//                oldItem: MyselfData,
//                newItem: MyselfData
//            ): Boolean =
//                oldItem == newItem
//        }
//    }
//
//    fun convertToDate(dateString: String): Date {
//        val format = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
//        return format.parse(dateString) ?: Date()
//    }
//
//    fun convertToTime(timeString: String): Date {
//        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
//        return format.parse(timeString) ?: Date()
//    }
//
//    fun showPopupMenu(context: Context, view: View, item: MyselfData) {
//        val popupMenu = PopupMenu(context, view)
//        val inflater = popupMenu.menuInflater
//        inflater.inflate(R.menu.myself_pop_up_menu, popupMenu.menu)
//
//        popupMenu.setOnMenuItemClickListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.edit_item -> {
//                    itemClick?.invoke(item)
//                    true
//                }
//                R.id.delete_item -> {
//                    itemClick?.invoke(item)
//                    true
//                }
//                else -> false
//            }
//        }
//
//        view.setOnClickListener {
//            popupMenu.show()
//        }
//    }
//}
