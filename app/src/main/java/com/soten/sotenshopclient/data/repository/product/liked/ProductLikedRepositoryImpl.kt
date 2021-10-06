package com.soten.sotenshopclient.data.repository.product.liked

import com.soten.sotenshopclient.data.db.dao.LikedDao
import com.soten.sotenshopclient.data.db.entity.LikedEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductLikedRepositoryImpl @Inject constructor(
    private val likedDao: LikedDao
): ProductLikedRepository {

    override suspend fun getAllLikedProduct(): List<LikedEntity> = withContext(Dispatchers.IO) {
        likedDao.getAllLikedProduct()
    }


    override suspend fun insertProduct(product: LikedEntity) = withContext(Dispatchers.IO) {
        likedDao.insertProduct(product)
    }

    override suspend fun deleteProduct(product: LikedEntity) = withContext(Dispatchers.IO) {
        likedDao.deleteProduct(product)
    }

}