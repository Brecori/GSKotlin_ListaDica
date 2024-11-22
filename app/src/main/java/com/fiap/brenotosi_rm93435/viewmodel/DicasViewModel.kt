package com.fiap.brenotosi_rm93435.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.fiap.brenotosi_rm93435.data.DicaDao
import com.fiap.brenotosi_rm93435.data.DicaDatabase
import com.fiap.brenotosi_rm93435.model.DicaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DicasViewModel(application: Application) : AndroidViewModel(application) {


    private val dicaDao: DicaDao

    val dicasLiveData: LiveData<List<DicaModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            DicaDatabase::class.java,
            "dicas_database"
        ).build()


        dicaDao = database.dicaDao()

        dicasLiveData = dicaDao.getAll()
    }
    fun addItem(titulo: String, descricao: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newDica = DicaModel(titulo = titulo, descricao = descricao)
            dicaDao.insert(newDica)
        }
    }

}