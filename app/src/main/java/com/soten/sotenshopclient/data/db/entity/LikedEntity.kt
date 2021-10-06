package com.soten.sotenshopclient.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.soten.sotenshopclient.data.response.product.ProductResponse
import com.soten.sotenshopclient.domain.model.ProductModel
import com.soten.sotenshopclient.util.converter.RoomTypeConverters

@Entity(tableName = "liked")
@TypeConverters(RoomTypeConverters::class)
data class LikedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val product: ProductModel,
    val createdAt: String,
)