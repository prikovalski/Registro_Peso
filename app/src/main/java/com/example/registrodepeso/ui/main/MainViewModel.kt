package com.example.registrodepeso.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.registrodepeso.data.Medicao
import com.example.registrodepeso.data.repository.MedicaoRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var medicaoRepository = MedicaoRepository(mutableListOf());
    private val _listaDeMedicoes = MutableLiveData<List<Medicao>>();

    val listadeMedicoes: LiveData<List<Medicao>> = _listaDeMedicoes;

    fun iniciarDados() {
        _listaDeMedicoes.value = medicaoRepository.retornarLista();
    }

    fun salvarMedicoes(medicao: Medicao) {
        medicaoRepository.salvar(medicao = medicao);
        atualizarListaMedicoes();
    }

    private fun atualizarListaMedicoes() {
        _listaDeMedicoes.value = medicaoRepository.retornarLista()
    }

    fun limparListaDeMedicao(){
        medicaoRepository.limparLista();
        atualizarListaMedicoes();
    }
}