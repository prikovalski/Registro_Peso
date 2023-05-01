package com.example.registrodepeso.ui.cadastro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.registrodepeso.R
import com.example.registrodepeso.data.Medicao
import com.example.registrodepeso.databinding.ActivityCadastroBinding
import com.example.registrodepeso.ui.main.MainActivity
import com.example.registrodepeso.ui.util.fromUTC
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class CadastroActivity : AppCompatActivity() {

    private var dataFinal: Date? = null
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepararListeners()
    }

    private fun prepararListeners() {
        configurarBotaoCancelarListener()
        configurarBotaoSalvarListener()
        configurarCapturaDataListener()
    }

    private fun configurarCapturaDataListener() {
        binding.cvData.setOnClickListener {
            exibirCalendario()
        }
    }

    private fun exibirCalendario() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.informe_a_data)
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.show(supportFragmentManager, "SELECAO_DATA")

        datePicker.addOnPositiveButtonClickListener { data ->
            if (data != null) {
                dataFinal = data.fromUTC()
                binding.tvData.text =
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(dataFinal)
            }
        }
    }

    private fun configurarBotaoSalvarListener() {
        binding.btnSalvar.setOnClickListener {
            Log.v("Kovalski::v", "Botão Salvar Acionado")
            salvarPeso()
        }
    }

    private fun salvarPeso() {
        val peso: String
        val data: String


        binding.apply {
            peso = etPeso.text.toString()
            data = tvData.text.toString()

            if (peso.isNotBlank() && data != getString(R.string.informe_a_data)) {
                Intent().apply {
                    putExtra( MainActivity.RETORNO,
                        Medicao(peso = peso.toDouble(), data = dataFinal!!)
                    )
                    setResult(RESULT_OK, this)
                }
                finish()
            } else {
                tilPeso.error = if (peso.isBlank()) {
                    getString(R.string.erro_peso)
                } else {
                    null
                }

            }

            if (data == getString(R.string.informe_a_data)) {
                Toast.makeText(
                    this@CadastroActivity,
                    getString(R.string.erro_data),
                    Toast.LENGTH_LONG
                )
                    .show()
                tvData.setTextColor(getColor(R.color.red))
                cvData.apply {
                    strokeColor = getColor(R.color.red)
                    strokeWidth = 2
                }
            } else {
                tvData.setTextColor(getColor(R.color.black))
                cvData.apply {
                    strokeColor = getColor(R.color.purple_500)
                    strokeWidth = 3
                }
            }
        }
    }

    private fun configurarBotaoCancelarListener() {
        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }
}