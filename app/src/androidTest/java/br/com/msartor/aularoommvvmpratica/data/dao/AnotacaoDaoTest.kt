package br.com.msartor.aularoommvvmpratica.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.msartor.aularoommvvmpratica.data.database.BancoDeDados
import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.data.entity.Categoria
import com.google.common.truth.ExpectFailure
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AnotacaoDaoTest {

    private lateinit var bancoDeDados: BancoDeDados
    private lateinit var categoriaDao: CategoriaDao
    private lateinit var anotacaoDao: AnotacaoDao

    @Before
    fun setUp() {
        bancoDeDados = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BancoDeDados::class.java
        ).allowMainThreadQueries().build()
        categoriaDao = bancoDeDados.categoriaDao
        anotacaoDao = bancoDeDados.anotacaoDao
    }

    @Test
    fun salvarCategoria_verificaCategoriaCadastrada_retornaTrue() {
        val categoria = Categoria(0,"mercado")
        val idcategoria = categoriaDao.salvar(categoria)
        assertThat( idcategoria).isGreaterThan(0L)
    }

    @Test
    fun salvarAnotacao_verificaAnotacaoCadastrada_retornaTrue() {
        salvarCategoria_verificaCategoriaCadastrada_retornaTrue()
        val anotacao = Anotacao(0,"teste","teste",1)
        val idAnotacao = anotacaoDao.insert(anotacao)
        assertThat(idAnotacao).isGreaterThan(0L)
    }

    @Test
    fun listarAnotacao_verificaListagemAnotacoes_retornaLista() {
        salvarAnotacao_verificaAnotacaoCadastrada_retornaTrue()
        val anotacoes = anotacaoDao.listarAnotacaoECategoria()
        assertThat(anotacoes).isNotEmpty()
    }

    @After
    fun tearDown() {
        bancoDeDados.close()
    }
}