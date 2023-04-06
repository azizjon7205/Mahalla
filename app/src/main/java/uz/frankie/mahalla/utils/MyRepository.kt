package uz.frankie.mahalla.utils

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.frankie.mahalla.data.dao.MyselfDao
import uz.frankie.mahalla.data.entity.MyselfData

class MyRepository(private val myDao: MyselfDao) {

    suspend fun insertMyData(myData: MyselfData) {
        withContext(Dispatchers.IO) {
            myDao.insertMyData(myData)
        }
    }

    suspend fun getAllMyData(): LiveData<List<MyselfData>> {
        return withContext(Dispatchers.IO) {
            myDao.getAllMyData()
        }
    }
}