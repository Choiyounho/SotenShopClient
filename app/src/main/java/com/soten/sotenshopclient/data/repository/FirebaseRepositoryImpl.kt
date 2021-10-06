package com.soten.sotenshopclient.data.repository

import com.charlezz.pickle.data.entity.Media
import com.google.firebase.storage.FirebaseStorage
import com.soten.sotenshopclient.data.preference.SharedPreferenceKey.KEY_USER_ID
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import com.soten.sotenshopclient.util.TimeFormatUtil.createdTimeForRegisterProduct
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val sharedPreferenceManager: SharedPreferenceManager,
) : FirebaseRepository {

    override suspend fun uploadImagesAsync(mediaList: List<Media>): Deferred<List<String>> =
        CoroutineScope(Dispatchers.IO).async {
            val images = mutableListOf<String>()
            val uploadDeferred: List<Deferred<Any>> =
                mediaList.mapIndexed { index, media ->
                    async {
                        try {
                            val userId = sharedPreferenceManager.getInt(KEY_USER_ID)
                            val fileName =
                                "${userId}-${createdTimeForRegisterProduct()}-image${index}.png"
                            storage.reference.child("product/${userId}").child(fileName)
                                .putFile(media.getUri())
                                .await()
                                .storage
                                .downloadUrl
                                .await()
                                .toString().also {
                                    images.add(it)
                                }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Pair(media.getUri(), e)
                        }
                    }
                }
            uploadDeferred.awaitAll()
            images
        }
}