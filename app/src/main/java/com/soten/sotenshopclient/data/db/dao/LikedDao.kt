package com.soten.sotenshopclient.data.db.dao

import androidx.room.*
import com.soten.sotenshopclient.data.db.entity.LikedEntity
import com.soten.sotenshopclient.data.response.product.ProductResponse
import com.soten.sotenshopclient.domain.model.ProductModel

@Dao
interface LikedDao {

    @Query("SELECT * FROM liked ORDER BY createdAt DESC")
    suspend fun getAllLikedProduct(): List<LikedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: LikedEntity)

    @Delete
    suspend fun deleteProduct(product: LikedEntity)

}