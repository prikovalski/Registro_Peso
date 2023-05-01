package com.example.registrodepeso.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.registrodepeso.R
import com.example.registrodepeso.data.Medicao
import com.example.registrodepeso.databinding.ListItemMedicoesBinding
import java.text.SimpleDateFormat
import java.util.*

class MedicoesAdapter(
    private val context: Context,
    private val listaMedicoes: List<Medicao>
) :
    RecyclerView.Adapter<MedicoesAdapter.ViewHolder>(){
    private lateinit var binding: ListItemMedicoesBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind (item: Medicao) {
            binding.apply {
                tvPeso.text = context.getString(R.string.label_item_peso, item.peso)
                tvData.text = context.getString(R.string.label_item_data,
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(item.data))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListItemMedicoesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun getItemCount() = listaMedicoes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = listaMedicoes[position])
    }
}