package com.soten.sotenshopclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.soten.sotenshopclient.data.db.dao.BasketDao
import com.soten.sotenshopclient.data.db.dao.LikedDao
import com.soten.sotenshopclient.data.db.entity.BasketEntity
import com.soten.sotenshopclient.data.db.entity.LikedEntity
import com.soten.sotenshopclient.util.converter.RoomTypeConverters

@Database(
    entities = [BasketEntity::class, LikedEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingDataBase : RoomDatabase() {

    abstract fun basketDao(): BasketDao
    abstract fun LikedDao(): LikedDao

}