package br.com.msartor.aularoommvvmpratica.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.data.entity.Categoria
import br.com.msartor.aularoommvvmpratica.data.repository.AnotacaoRepository
import br.com.msartor.aularoommvvmpratica.data.repository.CategoriaRepository
import br.com.msartor.aularoommvvmpratica.data.repository.ResultadoOperacao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnotacaoViewModel @Inject constructor(
    private val anotacaoRepository: AnotacaoRepository
): ViewModel() {

    private val _resultadoOperacao = MutableLiveData<ResultadoOperacao>()
    val resultadoOperacao: LiveData<ResultadoOperacao> = _resultadoOperacao

    fun salvar(anotacao: Anotacao){
        if(validarDadosAnotacao(anotacao)) {
            viewModelScope.launch(Dispatchers.IO) {
                val resultado = anotacaoRepository.salvar(anotacao)
                _resultadoOperacao.postValue(resultado)
            }
        }
    }

    fun validarDadosAnotacao(anotacao: Anotacao): Boolean {
        if (anotacao.titulo.isBlank()) {
            _resultadoOperacao.value = ResultadoOperacao(false, "Preencha o campo Titulo")
            return false
        }
        if (anotacao.descricao.isBlank()) {
            _resultadoOperacao.value = ResultadoOperacao(false, "Preencha o campo Descrição")
            return false
        }
        if (anotacao.categoriaId.toString().isBlank()) {
            _resultadoOperacao.value = ResultadoOperacao(false, "Selecione uma Categoria")
            return false
        }

        return true
    }
}