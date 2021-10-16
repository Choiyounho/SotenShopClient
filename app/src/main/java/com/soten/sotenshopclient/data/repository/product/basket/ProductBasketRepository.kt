package com.soten.sotenshopclient.data.repository.product.basket

import com.soten.sotenshopclient.data.db.entity.BasketEntity
import com.soten.sotenshopclient.data.db.entity.LikedEntity
import com.soten.sotenshopclient.domain.model.ProductModel

interface ProductBasketRepository {

    suspend fun getAllBasketProduct(): List<BasketEntity>

    suspend fun insertProduct(product: BasketEntity)

    suspend fun deleteProduct(product: BasketEntity)

    suspend fun updateCount(entityId: Int, plusCount: Int)

    suspend fun deleteAll()

}