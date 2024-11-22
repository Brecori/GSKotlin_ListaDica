package com.fiap.brenotosi_rm93435.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fiap.brenotosi_rm93435.model.DicaModel

@Dao
interface DicaDao {
    @Query("SELECT * FROM DicaModel")
    fun getAll(): LiveData<List<DicaModel>>

    @Insert
    fun insert(dica: DicaModel)

    @Delete
    fun delete(dica: DicaModel)
}