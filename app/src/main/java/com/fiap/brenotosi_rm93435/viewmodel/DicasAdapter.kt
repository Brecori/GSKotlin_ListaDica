package com.fiap.brenotosi_rm93435.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fiap.brenotosi_rm93435.R
import com.fiap.brenotosi_rm93435.model.DicaModel

class DicasAdapter(private val onDicaRemoved: (DicaModel) -> Unit) :
    RecyclerView.Adapter<DicasAdapter.DicaViewHolder>() {

    private var dicas = mutableListOf<DicaModel>()
    private var dicasFiltradas = mutableListOf<DicaModel>()

    init {
        dicasFiltradas = dicas
    }

    inner class DicaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo = view.findViewById<TextView>(R.id.itemTitulo)
        val descricao = view.findViewById<TextView>(R.id.itemDescricao)
        val item = view.findViewById<LinearLayout>(R.id.item)

        fun bind(dica: DicaModel) {
            titulo.text = dica.titulo
            descricao.text = dica.descricao
            item.setOnClickListener {
                Toast.makeText(itemView.context, dica.titulo, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DicaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dica, parent, false)
        return DicaViewHolder(view)
    }

    override fun getItemCount(): Int = dicasFiltradas.size
    override fun onBindViewHolder(holder: DicaViewHolder, position: Int) {
        val item = dicasFiltradas[position]
        holder.bind(item)
    }

    fun updateItems(newDicas: MutableList<DicaModel>) {
        dicas = newDicas
        dicasFiltradas = newDicas
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        dicasFiltradas = if (query.isEmpty()) {
            dicas
        } else {
            dicas.filter {
                it.titulo.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }
}