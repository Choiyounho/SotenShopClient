package com.soten.sotenshopclient.data.db.dao

import androidx.room.*
import com.soten.sotenshopclient.data.db.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history ORDER BY createdAt DESC")
    suspend fun getAllHistory(): MutableList<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(historyEntity: HistoryEntity)

    @Delete
    suspend fun deleteHistory(historyEntity: HistoryEntity)

}