package com.soten.sotenshopclient.data.db.dao

import androidx.room.*
import com.soten.sotenshopclient.data.db.entity.BasketEntity

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket")
    suspend fun getAllBasketProduct(): List<BasketEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(basketEntity: BasketEntity)

    @Delete
    suspend fun deleteProduct(basketEntity: BasketEntity)

}