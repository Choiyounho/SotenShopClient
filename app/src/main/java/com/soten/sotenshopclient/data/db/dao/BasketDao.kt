package com.soten.sotenshopclient.data.db.dao

import androidx.room.*
import com.soten.sotenshopclient.data.db.entity.BasketEntity

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket ORDER BY createdAt DESC")
    suspend fun getAllBasketProduct(): List<BasketEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(basketEntity: BasketEntity)

    @Delete
    suspend fun deleteProduct(basketEntity: BasketEntity)

    @Query("UPDATE basket SET count=:plusCount WHERE id=:entityId")
    suspend fun updateCount(entityId: Int, plusCount: Int)

    @Query("DELETE FROM basket")
    suspend fun deleteAll()

}