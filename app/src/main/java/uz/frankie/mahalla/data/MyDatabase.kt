package uz.frankie.mahalla.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.frankie.mahalla.data.dao.MyselfDao
import uz.frankie.mahalla.data.entity.MyselfData

@Database(entities = [MyselfData::class], version = 2)
abstract class MyDatabase : RoomDatabase() {

    abstract fun myselfDao(): MyselfDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "myself_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
