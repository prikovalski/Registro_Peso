package com.example.registrodepeso.data.repository

import com.example.registrodepeso.data.Medicao

class MedicaoRepository(novaLista: MutableList<Medicao>) {
    private val listDB: MutableList<Medicao> = novaLista;

    fun salvar (medicao: Medicao) {
        listDB.add(medicao);
    }

    fun limparLista(){
        listDB.clear();
    }

    fun retornarLista() = listDB.toList();
}