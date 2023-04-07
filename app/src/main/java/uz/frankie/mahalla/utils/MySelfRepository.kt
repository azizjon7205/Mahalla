package uz.frankie.mahalla.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.frankie.mahalla.data.dao.MyselfDao
import uz.frankie.mahalla.data.entity.MyselfData

class MySelfRepository(private val myDao: MyselfDao) {

    suspend fun insertMyData(myData: MyselfData) {
        withContext(Dispatchers.IO) {
            myDao.insertItem(myData)
        }
    }

    suspend fun getAllMyData(): List<MyselfData> {
        return withContext(Dispatchers.IO) {
            myDao.getAllItems()
        }
    }
}