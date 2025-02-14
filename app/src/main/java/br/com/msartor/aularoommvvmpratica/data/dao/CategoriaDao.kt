package br.com.msartor.aularoommvvmpratica.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.msartor.aularoommvvmpratica.data.entity.Categoria

@Dao
interface CategoriaDao {

    @Insert
    fun salvar(categoria: Categoria):Long

    @Query("Select * From categotias")
    fun listar(): List<Categoria>
}