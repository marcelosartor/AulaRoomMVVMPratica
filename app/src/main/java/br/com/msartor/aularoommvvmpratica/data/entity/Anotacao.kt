package br.com.msartor.aularoommvvmpratica.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "anotacoes",
    foreignKeys = [
        ForeignKey(entity = Categoria::class,
            parentColumns = ["id"],
            childColumns = ["categoriaId"],
        )
    ])
data class Anotacao(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val titulo:String,
    val descricao:String,
    val categoriaId: Long // FK
)
