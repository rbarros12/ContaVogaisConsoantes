package com.rafael.contavogaisconsoantes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainViewModel : ViewModel() {
    private val _vogais = MutableLiveData<String>()
    val qtdVogais: LiveData<String>
        get() = _vogais

    private val _consoantes = MutableLiveData<String>()
    val qtdConsoantes: LiveData<String>
        get() = _consoantes

    val textoDigitado = MutableLiveData<String>()
    private val textoObserver = Observer<String> { contar(it) }

    init {
        textoDigitado.observeForever(textoObserver)
    }

    override fun onCleared() {
        textoDigitado.removeObserver(textoObserver)
    }

    private fun contar(texto: String) {
        val VOGAIS = "aeiou"
        val CONSOANTES = "bcdfghjklmnpqrstvwxyz"
        var totalVogais = 0
        var totalConsoantes = 0

        for (letra in texto.toLowerCase()) {
            if (letra in VOGAIS) {
                totalVogais++
            } else if (letra in CONSOANTES) {
                totalConsoantes++
            }
        }
        _vogais.value = totalVogais.toString()
        _consoantes.value = totalConsoantes.toString()
    }
}