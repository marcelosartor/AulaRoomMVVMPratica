package br.com.msartor.aularoommvvmpratica.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.msartor.aularoommvvmpratica.data.entity.relationship.AnotacaoECategoria
import br.com.msartor.aularoommvvmpratica.databinding.ItemAnotacaoBinding

class AnotacaoAdapter: Adapter<AnotacaoAdapter.AnotacaoViewHolder>() {

    // TODO ajustar lista
    private var listaAnotacoesCategoria = listOf<AnotacaoECategoria>()

    inner class AnotacaoViewHolder(private val bind: ItemAnotacaoBinding) : ViewHolder(bind.root){
        fun bind(item: AnotacaoECategoria){
            with(item) {
                bind.textTituloAnotacao.text = anotacao.titulo
                bind.textDescricaoAnotacao.text = anotacao.descricao
                bind.textCategoriaAnotacao.text = categoria.nome
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnotacaoViewHolder {
        val itemView = ItemAnotacaoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       return AnotacaoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaAnotacoesCategoria.size
    }

    override fun onBindViewHolder(holder: AnotacaoViewHolder, position: Int) {
        val item = listaAnotacoesCategoria[position]
        holder.bind(item)

    }

    fun configurarLista(list: List<AnotacaoECategoria>){
        listaAnotacoesCategoria = list
        notifyDataSetChanged()
    }
}