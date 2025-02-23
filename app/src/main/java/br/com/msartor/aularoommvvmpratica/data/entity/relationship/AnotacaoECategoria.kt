package br.com.msartor.aularoommvvmpratica.data.entity.relationship

import androidx.room.Embedded
import androidx.room.Relation
import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.data.entity.Categoria

data class AnotacaoECategoria(
    @Embedded
    val anotacao: Anotacao,
    @Relation(
        entityColumn = "id",
        parentColumn = "categoriaId"
    )
    val categoria: Categoria

)