package br.com.msartor.aularoommvvmpratica.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "anotacoes",
    foreignKeys = [
        ForeignKey(entity = Categoria::class,
            parentColumns = ["id"],
            childColumns = ["categoriaId"],
        )
    ])
@Parcelize
data class Anotacao(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val titulo:String,
    val descricao:String,
    val categoriaId: Long // FK
): Parcelable
