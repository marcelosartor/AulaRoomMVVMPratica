package br.com.msartor.aularoommvvmpratica.data.repository

import br.com.msartor.aularoommvvmpratica.data.entity.Categoria

interface CategoriaRepository {
    suspend fun salvar(categoria: Categoria): ResultadoOperacao
    suspend fun listar(): List<Categoria>
}