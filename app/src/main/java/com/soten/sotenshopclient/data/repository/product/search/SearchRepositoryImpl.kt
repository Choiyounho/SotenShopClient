package com.soten.sotenshopclient.data.repository.product.search

import com.soten.sotenshopclient.data.db.dao.HistoryDao
import com.soten.sotenshopclient.data.db.entity.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val historyDao: HistoryDao
): SearchRepository {

    override suspend fun getAllHistory(): MutableList<HistoryEntity> {
        return historyDao.getAllHistory()
    }

    override suspend fun insertHistory(historyEntity: HistoryEntity) {
        return historyDao.insertHistory(historyEntity)
    }

    override suspend fun deleteHistory(historyEntity: HistoryEntity) {
        return historyDao.deleteHistory(historyEntity)
    }

}