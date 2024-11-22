package com.fiap.brenotosi_rm93435

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.fiap.brenotosi_rm93435.viewmodel.DicasAdapter
import com.fiap.brenotosi_rm93435.viewmodel.DicasViewModel
import com.fiap.brenotosi_rm93435.viewmodel.DicasViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: DicasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "EcoDicas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val dicasAdapter = DicasAdapter { _ ->
        }
        recyclerView.adapter = dicasAdapter

        val button = findViewById<Button>(R.id.button)
        val title = findViewById<EditText>(R.id.titulo)
        val description = findViewById<EditText>(R.id.descricao)

        button.setOnClickListener {
            if (title.text.isEmpty()) {
                title.error = "Preencha um valor"
                return@setOnClickListener
            }

            if (description.text.isEmpty()) {
                description.error = "Preencha um valor"
                return@setOnClickListener
            }

            viewModel.addItem(title.text.toString(), description.text.toString())
            title.text.clear()
            description.text.clear()
        }

        val viewModelFactory = DicasViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DicasViewModel::class.java)

        viewModel.dicasLiveData.observe(this) { items ->
            dicasAdapter.updateItems(items.toMutableList())
        }

        val searchView = findViewById<SearchView>(R.id.search_view)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                dicasAdapter.filter(newText ?: "")
                return true
            }
        })


    }
}