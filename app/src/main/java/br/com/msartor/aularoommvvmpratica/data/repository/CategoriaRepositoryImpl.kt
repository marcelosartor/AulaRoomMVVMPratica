package br.com.msartor.aularoommvvmpratica.data.repository

import br.com.msartor.aularoommvvmpratica.data.dao.CategoriaDao
import br.com.msartor.aularoommvvmpratica.data.entity.Categoria
import javax.inject.Inject

class CategoriaRepositoryImpl @Inject constructor(
    private val categoriaDao: CategoriaDao
) : CategoriaRepository{

    override suspend fun salvar(categoria: Categoria): ResultadoOperacao {
        val categoriaId = categoriaDao.salvar(categoria)
        if (categoriaId > 0) {
            return ResultadoOperacao(
                sucesso = true,
                mensagem = "Categoria salva com sucesso"
            )
        } else {
            return ResultadoOperacao(
                sucesso = false,
                mensagem = "Erro ao salvar categoria")
        }
    }
}