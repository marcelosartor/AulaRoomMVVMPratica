package br.com.msartor.aularoommvvmpratica.data.repository

import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.data.entity.Categoria

interface AnotacaoRepository {
    suspend fun salvar(anotacao: Anotacao): ResultadoOperacao
}