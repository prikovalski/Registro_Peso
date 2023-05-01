package com.example.registrodepeso.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.registrodepeso.R
import com.example.registrodepeso.data.Medicao
import com.example.registrodepeso.databinding.ActivityMainBinding
import com.example.registrodepeso.ui.cadastro.CadastroActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    private val retornoCadastro = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        activityResult ->
         if (activityResult.resultCode == RESULT_OK){
             activityResult.data.let {intent ->
                 if (intent != null) {
                     if (intent.hasExtra(RETORNO)) {
                         Log.i("PUC_MINAS::I::",
                             "Retorno da activity de Cadastro = ${
                                 intent.getParcelableExtra<Medicao>(RETORNO)
                             }"
                         )
                         mainViewModel.salvarMedicoes(intent.getParcelableExtra(RETORNO)!!)
                     }
                 }

             }
         }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);
        prepararListeners();
        prepararObservers();
        iniciarDados();
    }

    private fun iniciarDados() {
        mainViewModel.iniciarDados();
    }

    private fun prepararObservers() {
        prepararListaMedicoesObserver()
    }

    private fun prepararListaMedicoesObserver() {
        mainViewModel.listadeMedicoes.observe(this) {
            listaMedicoes -> atualizarRecicleView(listaMedicoes)
        }
    }

    private fun atualizarRecicleView(listaMedicoes: List<Medicao>?) {
        if(listaMedicoes.isNullOrEmpty()) {
            binding.apply {
                rvMedicoes.visibility = View.GONE //retira o elemento de lista da tela para exibir mensagem de lista vazia
                tvMensagemListaVazia.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                tvMensagemListaVazia.visibility = View.GONE
                rvMedicoes.apply {  //neste caso utiliza o adapter pq vai chamar varias funçoes
                    visibility = View.VISIBLE
                    adapter = MedicoesAdapter(context = context, listaMedicoes = listaMedicoes)
                }
            }
        }
    }

    private fun prepararListeners() {
        configurarFABListener()
    }

    private fun configurarFABListener() {
        binding.fabAddPeso.setOnClickListener{
            Intent(this, CadastroActivity::class.java).let {
                retornoCadastro.launch(it)
            }
        }
    }

    companion object {
        const val RETORNO = "PESO_RETORNO"
    }
}