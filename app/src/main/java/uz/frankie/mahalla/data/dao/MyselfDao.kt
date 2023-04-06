package uz.frankie.mahalla.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.frankie.mahalla.data.entity.MyselfData

@Dao
interface MyselfDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyData(myselfData: MyselfData)

    @Query("SELECT * FROM my_data_table")
    suspend fun getAllMyData(): LiveData<List<MyselfData>>
}