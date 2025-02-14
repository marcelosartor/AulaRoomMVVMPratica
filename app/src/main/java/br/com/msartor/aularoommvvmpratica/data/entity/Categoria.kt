package br.com.msartor.aularoommvvmpratica.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categotias")
data class Categoria(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val nome:String,

){}