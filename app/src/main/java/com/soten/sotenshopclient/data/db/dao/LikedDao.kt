package com.soten.sotenshopclient.data.db.dao

import androidx.room.*
import com.soten.sotenshopclient.data.db.entity.LikedEntity

@Dao
interface LikedDao {

    @Query("SELECT * FROM liked ORDER BY createdAt DESC")
    suspend fun getAllLikedProduct(): List<LikedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: LikedEntity)

    @Delete
    suspend fun deleteProduct(product: LikedEntity)

    @Query("SELECT * FROM LIKED WHERE id=:entityId")
    suspend fun getLikedEntityById(entityId: Int): LikedEntity?

}