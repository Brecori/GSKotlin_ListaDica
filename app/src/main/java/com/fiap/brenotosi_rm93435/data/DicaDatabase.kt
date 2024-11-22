package com.fiap.brenotosi_rm93435.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fiap.brenotosi_rm93435.model.DicaModel

@Database(entities = [DicaModel::class], version = 1)
abstract class DicaDatabase : RoomDatabase() {
    abstract fun dicaDao(): DicaDao
}