package com.soten.sotenshopclient.util.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.soten.sotenshopclient.domain.model.ProductModel

class RoomTypeConverters {

    @TypeConverter
    fun productToString(product: ProductModel) = Gson().toJson(product)

    @TypeConverter
    fun stringToProduct(productString: String): ProductModel {
        return Gson().fromJson(productString, ProductModel::class.java)
    }

}