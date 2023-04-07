package uz.frankie.mahalla.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.frankie.mahalla.data.entity.MyselfData

class MySelfViewModel(private val repository: MySelfRepository) : ViewModel() {

    fun insertMyData(myData: MyselfData) {
        viewModelScope.launch {
            repository.insertMyData(myData)
        }
    }

    suspend fun getAllMyData() {
        repository.getAllMyData()
    }
}