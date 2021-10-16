package com.soten.sotenshopclient.ui.home.search.searchresult

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.soten.sotenshopclient.data.repository.paging.PagingRepository
import com.soten.sotenshopclient.data.response.shopping.product.ProductResponse
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch

class SearchResultViewModel @AssistedInject constructor(
    private val pagingRepository: PagingRepository,
    @Assisted private val keyword: String,
) : ViewModel() {

    private val _keywordLiveData = MutableLiveData(keyword)
    val keywordLiveData: LiveData<String> get() = _keywordLiveData

    private val _dataSize = MutableLiveData<Int>()
    val dataSize: LiveData<Int> get() = _dataSize

    fun fetchProductFlow(): Flow<PagingData<ProductResponse>> {
        return pagingRepository.getSearchPagingData(keyword)
            .cachedIn(viewModelScope)
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(keyword: String): SearchResultViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            keyword: String,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(keyword) as T
            }
        }
    }

}