package com.soten.sotenshopclient.ui.home.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.sotenshopclient.data.db.entity.HistoryEntity
import com.soten.sotenshopclient.data.repository.product.search.SearchRepository
import com.soten.sotenshopclient.util.TimeFormatUtil.createdTimeForRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
) : ViewModel() {

    private val _historyLiveData = MutableLiveData<List<HistoryEntity>>()
    val historyLiveData: LiveData<List<HistoryEntity>> get() = _historyLiveData

    init {
        fetchHistories()
    }

    private fun fetchHistories() = viewModelScope.launch {
        _historyLiveData.value = searchRepository.getAllHistory()
    }

    fun insertHistory(keyword: String) = viewModelScope.launch {
        searchRepository.insertHistory(
            HistoryEntity(
                keyword = keyword,
                createdAt = createdTimeForRegister()))

        if (searchRepository.getAllHistory().size > 30) {
            val list =
                searchRepository.getAllHistory().sortedByDescending { it.createdAt }.toMutableList()

            val historyEntity = list.last()
            deleteHistory(historyEntity)
        }
        fetchHistories()
    }

    fun deleteHistory(historyEntity: HistoryEntity) = viewModelScope.launch {
        searchRepository.deleteHistory(historyEntity)

        fetchHistories()
    }

}