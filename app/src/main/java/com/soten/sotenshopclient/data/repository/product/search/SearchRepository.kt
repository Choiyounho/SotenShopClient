package com.soten.sotenshopclient.data.repository.product.search

import com.soten.sotenshopclient.data.db.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun getAllHistory(): MutableList<HistoryEntity>

    suspend fun insertHistory(historyEntity: HistoryEntity)

    suspend fun deleteHistory(historyEntity: HistoryEntity)

}