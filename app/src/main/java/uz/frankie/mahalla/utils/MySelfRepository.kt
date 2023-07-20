package uz.frankie.mahalla.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.frankie.mahalla.data.dao.MyselfDao
import uz.frankie.mahalla.data.entity.MyselfData
import javax.inject.Inject

class MySelfRepository @Inject constructor(private val myDao: MyselfDao) {

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

    suspend fun deleteMyData(myData:MyselfData) {
        return withContext(Dispatchers.IO) {
            myDao.deleteItem(myData)
        }
    }

    suspend fun updateMyData(myData:MyselfData) {
        return withContext(Dispatchers.IO) {
            myDao.updateItem(myData)
        }
    }
}