package br.com.msartor.aularoommvvmpratica.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.data.entity.relationship.AnotacaoECategoria

@Dao
interface AnotacaoDao {
    @Insert
    fun salvar(anotacao: Anotacao):Long

    @Query("Select * From anotacoes")
    fun listar(): List<Anotacao>

    @Query("Select * From anotacoes")
    fun listarAnotacaoECategoria(): List<AnotacaoECategoria>

}