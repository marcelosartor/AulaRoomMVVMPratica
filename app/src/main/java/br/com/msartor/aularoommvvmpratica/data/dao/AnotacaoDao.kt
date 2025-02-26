package br.com.msartor.aularoommvvmpratica.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.data.entity.relationship.AnotacaoECategoria

@Dao
interface AnotacaoDao {
    @Insert
    fun insert(anotacao: Anotacao):Long

    @Update
    fun update(anotacao: Anotacao):Int

    @Delete
    fun remover(anotacao: Anotacao):Int

    @Query("Select * From anotacoes")
    fun listar(): List<Anotacao>

    @Query("Select * From anotacoes")
    fun listarAnotacaoECategoria(): List<AnotacaoECategoria>

    @Query("""Select * From anotacoes a  
        Where a.titulo like '%'||:text||'%' 
           or a.descricao like '%'||:text||'%' """)
    fun pesquisarAnotacaoECategoria(text: String): List<AnotacaoECategoria>

}