package br.com.msartor.aularoommvvmpratica.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.msartor.aularoommvvmpratica.data.dao.AnotacaoDao
import br.com.msartor.aularoommvvmpratica.data.dao.CategoriaDao
import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.data.entity.Categoria
import br.com.msartor.aularoommvvmpratica.helper.Contatantes

@Database(
    entities = [Categoria::class,Anotacao::class],
    version = 1
)
abstract class BancoDeDados: RoomDatabase() {
    abstract val categoriaDao: CategoriaDao
    abstract val anotacaoDao: AnotacaoDao

    companion object{
        fun getInstance(context: Context): BancoDeDados {
            return Room.databaseBuilder(
                context = context,
                name = Contatantes.NOME_BANCO_DE_DADOS,
                klass = BancoDeDados::class.java)
                .build()
        }
    }
}