package uz.frankie.mahalla.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.frankie.mahalla.data.entity.MyselfData
import javax.inject.Inject

@HiltViewModel
class MySelfViewModel @Inject constructor(private val mySelfRepository: MySelfRepository) : ViewModel() {

    private val _listMySelfDataState = MutableStateFlow<UiStateList<MyselfData>>(UiStateList.EMPTY)
    val listMySelfDataState = _listMySelfDataState.asStateFlow()

    fun getListMySelfData() = viewModelScope.launch {
        _listMySelfDataState.value = UiStateList.LOADING
        try {
            val listMySelf = mySelfRepository.getAllMyData()
            _listMySelfDataState.value = UiStateList.SUCCESS(listMySelf)
        }catch (e:java.lang.Exception){
            _listMySelfDataState.value = UiStateList.ERROR(e.localizedMessage ?: "ERROR_MESSAGE")
        }

    }

    private val _insertMyDataState = MutableStateFlow<UiStateObject<Unit>>(UiStateObject.EMPTY)
    val insertMyDataState: StateFlow<UiStateObject<Unit>> = _insertMyDataState

    fun insertMyData(data: MyselfData) = viewModelScope.launch {
        _insertMyDataState.value = UiStateObject.LOADING
        try {
            mySelfRepository.insertMyData(data)
            _insertMyDataState.value = UiStateObject.SUCCESS(Unit)
        } catch (e: Exception) {
            _insertMyDataState.value = UiStateObject.ERROR(e.localizedMessage ?: "ERROR_MESSAGE")
        }
    }







}