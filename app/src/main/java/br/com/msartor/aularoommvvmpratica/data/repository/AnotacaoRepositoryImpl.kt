package br.com.msartor.aularoommvvmpratica.data.repository

import br.com.msartor.aularoommvvmpratica.data.dao.AnotacaoDao
import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.data.entity.relationship.AnotacaoECategoria
import javax.inject.Inject

class AnotacaoRepositoryImpl @Inject constructor(
    private val anotacaoDao: AnotacaoDao
): AnotacaoRepository {

    override suspend fun salvar(anotacao: Anotacao): ResultadoOperacao {
        return if (anotacao.id > 0) {
            update(anotacao)
        }else{
            insert(anotacao)
        }

    }

    private fun insert(anotacao: Anotacao): ResultadoOperacao {
        val anotacaoId = anotacaoDao.insert(anotacao)
        return if (anotacaoId > 0) {
            ResultadoOperacao(
                sucesso = true,
                mensagem = "Anotação salva com sucesso"
            )
        } else {
            ResultadoOperacao(
                sucesso = false,
                mensagem = "Erro ao salvar anotação")
        }
    }

    private fun update(anotacao: Anotacao): ResultadoOperacao {
        val anotacaoId = anotacaoDao.update(anotacao)
        return if (anotacaoId > 0) {
            ResultadoOperacao(
                sucesso = true,
                mensagem = "Anotação editada com sucesso"
            )
        } else {
            ResultadoOperacao(
                sucesso = false,
                mensagem = "Erro ao editar anotação")
        }
    }


    override suspend fun listarAnotacaoECategoria(): List<AnotacaoECategoria> {
        return anotacaoDao.listarAnotacaoECategoria()
    }

    override suspend fun pesquisarAnotacaoECategoria(text: String): List<AnotacaoECategoria> {
        return anotacaoDao.pesquisarAnotacaoECategoria(text)
    }

    override suspend fun remover(anotacao: Anotacao): ResultadoOperacao {
        val qtdRegistros = anotacaoDao.remover(anotacao)
        if (qtdRegistros > 0) {
            return ResultadoOperacao(
                sucesso = true,
                mensagem = "Anotação removida com sucesso"
            )
        } else {
            return ResultadoOperacao(
                sucesso = false,
                mensagem = "Erro ao remover anotação")
        }
    }
}