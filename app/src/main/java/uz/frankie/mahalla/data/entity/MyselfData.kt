package uz.frankie.mahalla.data.entity

import android.widget.EditText
import android.widget.TextView
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_data_table")
data class MyselfData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val date: String,
    val time: String
)
