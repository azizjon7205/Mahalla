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
        private var instance: MyDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabase {

            if (instance == null)
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "myself_database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

            return instance!!
        }
    }
}
