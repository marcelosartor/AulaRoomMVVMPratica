package br.com.msartor.aularoommvvmpratica.data.repository

import br.com.msartor.aularoommvvmpratica.data.dao.AnotacaoDao
import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.data.entity.relationship.AnotacaoECategoria
import javax.inject.Inject

class AnotacaoRepositoryImpl @Inject constructor(
    private val anotacaoDao: AnotacaoDao
): AnotacaoRepository {

    override suspend fun salvar(anotacao: Anotacao): ResultadoOperacao {
        val anotacaoId = anotacaoDao.salvar(anotacao)
        if (anotacaoId > 0) {
            return ResultadoOperacao(
                sucesso = true,
                mensagem = "Anotação salva com sucesso"
            )
        } else {
            return ResultadoOperacao(
                sucesso = false,
                mensagem = "Erro ao salvar anotação")
        }
    }

    override suspend fun listarAnotacaoECategoria(): List<AnotacaoECategoria> {
        return anotacaoDao.listarAnotacaoECategoria()
    }
}