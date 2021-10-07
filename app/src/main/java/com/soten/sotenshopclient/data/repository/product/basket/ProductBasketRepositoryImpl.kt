package com.soten.sotenshopclient.data.repository.product.basket

import com.soten.sotenshopclient.data.db.dao.BasketDao
import com.soten.sotenshopclient.data.db.entity.BasketEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductBasketRepositoryImpl @Inject constructor(
    private val basketDao: BasketDao,
) : ProductBasketRepository {

    override suspend fun getAllBasketProduct(): List<BasketEntity> {
        return basketDao.getAllBasketProduct()
    }

    override suspend fun insertProduct(product: BasketEntity) {
        return basketDao.insertProduct(product)
    }

    override suspend fun deleteProduct(product: BasketEntity) {
        return basketDao.deleteProduct(product)
    }

    override suspend fun updateCount(entityId: Int, plusCount: Int) {
        return basketDao.updateCount(entityId, plusCount)
    }

}