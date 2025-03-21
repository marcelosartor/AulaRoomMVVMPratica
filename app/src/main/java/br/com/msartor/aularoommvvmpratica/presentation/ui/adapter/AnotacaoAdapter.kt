package br.com.msartor.aularoommvvmpratica.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.msartor.aularoommvvmpratica.R
import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.data.entity.relationship.AnotacaoECategoria
import br.com.msartor.aularoommvvmpratica.databinding.ItemAnotacaoBinding
import kotlin.random.Random

class AnotacaoAdapter(
    private val onClickRemover:(Anotacao)->Unit,
    private val onClickAtualizar:(Anotacao)->Unit,
    ): Adapter<AnotacaoAdapter.AnotacaoViewHolder>() {

    // TODO ajustar lista
    private var listaAnotacoesCategoria = listOf<AnotacaoECategoria>()

    inner class AnotacaoViewHolder(private val binding: ItemAnotacaoBinding) : ViewHolder(binding.root){
        fun bind(item: AnotacaoECategoria){
            with(item) {
                binding.textTituloAnotacao.text = anotacao.titulo
                binding.textDescricaoAnotacao.text = anotacao.descricao
                binding.textCategoriaAnotacao.text = categoria.nome
            }
            binding.cardItem.setCardBackgroundColor(
                ContextCompat.getColor(
                    binding.cardItem.context,
                    gerarCorAleatoria()
                )
            )
            binding.btnRemoverAnotacao.setOnClickListener{
                onClickRemover(item.anotacao)
            }

            binding.cardItem.setOnClickListener{
                onClickAtualizar(item.anotacao)
            }

        }
    }

    private fun gerarCorAleatoria(): Int {
        val listaCores = listOf(
            R.color.laranja, R.color.roxo, R.color.azul,
            R.color.rosa, R.color.amarelo, R.color.bege
        )
        val numeroAleatorio =  Random.nextInt( listaCores.size )
        return listaCores[ numeroAleatorio ]
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