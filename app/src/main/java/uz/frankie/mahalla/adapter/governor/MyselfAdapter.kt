package uz.frankie.mahalla.adapter.governor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.frankie.mahalla.R
import uz.frankie.mahalla.data.entity.MyselfData


class MyselfAdapter : ListAdapter<MyselfData, MyDataViewHolder>(MyselfDataDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_self, parent, false)
        return MyDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyDataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MyDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(myData: MyselfData) {
        itemView.findViewById<TextView>(R.id.edt_write).text = myData.name
        itemView.findViewById<TextView>(R.id.tv_date).text = myData.date
        itemView.findViewById<TextView>(R.id.tv_time).text = myData.time
    }
}

class MyselfDataDiffCallback : DiffUtil.ItemCallback<MyselfData>() {

    override fun areItemsTheSame(oldItem: MyselfData, newItem: MyselfData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MyselfData, newItem: MyselfData): Boolean {
        return oldItem == newItem
    }
}