package com.soten.sotenshopclient.data.repository.product.liked

import com.soten.sotenshopclient.data.db.entity.LikedEntity
import com.soten.sotenshopclient.domain.model.ProductModel

interface ProductLikedRepository {

    suspend fun getAllLikedProduct(): List<LikedEntity>

    suspend fun insertProduct(productResponse: LikedEntity)

    suspend fun deleteProduct(productResponse: LikedEntity)

}