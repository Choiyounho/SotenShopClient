package com.soten.sotenshopclient.data.repository

import com.charlezz.pickle.data.entity.Media
import kotlinx.coroutines.Deferred

interface FirebaseRepository {

    suspend fun uploadImagesAsync(mediaList: List<Media>): Deferred<List<String>>

}