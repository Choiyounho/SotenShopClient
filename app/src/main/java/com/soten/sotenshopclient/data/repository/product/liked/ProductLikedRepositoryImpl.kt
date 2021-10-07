package com.soten.sotenshopclient.data.repository.product.liked

import com.soten.sotenshopclient.data.db.dao.LikedDao
import com.soten.sotenshopclient.data.db.entity.LikedEntity
import javax.inject.Inject

class ProductLikedRepositoryImpl @Inject constructor(
    private val likedDao: LikedDao,
) : ProductLikedRepository {

    override suspend fun getAllLikedProduct(): List<LikedEntity> {
        return likedDao.getAllLikedProduct()
    }

    override suspend fun insertProduct(product: LikedEntity) {
        return likedDao.insertProduct(product)
    }

    override suspend fun deleteProduct(product: LikedEntity) {
        return likedDao.deleteProduct(product)
    }

}