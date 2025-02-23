package br.com.msartor.aularoommvvmpratica.data.repository

import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.data.entity.Categoria
import br.com.msartor.aularoommvvmpratica.data.entity.relationship.AnotacaoECategoria

interface AnotacaoRepository {
    suspend fun salvar(anotacao: Anotacao): ResultadoOperacao
    suspend fun listarAnotacaoECategoria(): List<AnotacaoECategoria>


}