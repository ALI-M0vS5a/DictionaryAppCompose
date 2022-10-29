package com.example.dictionaryappcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.dictionaryappcompose.data.local.converter.Converters
import com.example.dictionaryappcompose.data.local.entity.WordInfoEntity


@TypeConverters(Converters::class)
@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
abstract class WordInfoDatabase: RoomDatabase() {
    abstract val dao: WordInfoDao
}