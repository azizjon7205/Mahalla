package uz.frankie.mahalla.adapter.governor

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


class MyselfAdapter() :
    RecyclerView.Adapter<MyselfAdapter.ViewHolder>() {
    private val dif = AsyncListDiffer(this, ITEM_DIF)

    fun submitData(list: List<MyselfData>) {
        val sortedList = list.sortedWith(compareBy<MyselfData> { convertToDate(it.date) }
            .thenBy { convertToTime(it.time) })

        dif.submitList(sortedList)
        Log.d("DIFLIST", "submitData: ${list}")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_my_self, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dif.currentList[position]
        holder.nameTextView.text = item.text
        holder.dateTextView.text = item.date
        holder.timeTextView.text = item.time
    }

    override fun getItemCount(): Int {
        return dif.currentList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tv_information)
        val dateTextView: TextView = itemView.findViewById(R.id.tv_date)
        val timeTextView: TextView = itemView.findViewById(R.id.tv_time)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<MyselfData>() {

            override fun areItemsTheSame(oldItem: MyselfData, newItem: MyselfData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MyselfData,
                newItem: MyselfData
            ): Boolean =
                oldItem == newItem
        }
    }

    fun convertToDate(dateString: String): Date {
        val format = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return format.parse(dateString) ?: Date()
    }

    fun convertToTime(timeString: String): Date {
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        return format.parse(timeString) ?: Date()
    }
}
