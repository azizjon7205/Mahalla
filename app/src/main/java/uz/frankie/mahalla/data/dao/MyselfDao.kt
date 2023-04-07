package uz.frankie.mahalla.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.frankie.mahalla.data.entity.MyselfData

@Dao
interface MyselfDao {

    @Query("SELECT * FROM items")
    fun getAllItems(): List<MyselfData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: MyselfData)

    @Delete
    suspend fun deleteItem(item: MyselfData)

    @Update
    suspend fun updateItem(item: MyselfData)
}