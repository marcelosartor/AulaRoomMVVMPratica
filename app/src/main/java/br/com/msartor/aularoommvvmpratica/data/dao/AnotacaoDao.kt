package br.com.msartor.aularoommvvmpratica.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao

@Dao
interface AnotacaoDao {
    @Insert
    fun salvar(anotacao: Anotacao):Long

    @Query("Select * From anotacoes")
    fun listar(): List<Anotacao>


}