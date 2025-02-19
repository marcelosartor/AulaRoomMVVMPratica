package br.com.msartor.aularoommvvmpratica.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.msartor.aularoommvvmpratica.R
import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.databinding.ActivityCadastroAnotacaoBinding
import br.com.msartor.aularoommvvmpratica.presentation.viewmodel.AnotacaoViewModel
import br.com.msartor.aularoommvvmpratica.presentation.viewmodel.CategoriaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroAnotacaoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCadastroAnotacaoBinding.inflate(layoutInflater)
    }

    private val anotacaoViewModel: AnotacaoViewModel by viewModels()
    private val categoriaViewModel: CategoriaViewModel by viewModels()

    private lateinit var spinnerAadapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarToolbar()
        inicializarUI()
        inicializarListeners()
        inicializarObservables()
    }

    override fun onStart() {
        super.onStart()
        categoriaViewModel.listar()
    }

    private fun inicializarUI() {
        with(binding) {
            spinnerAadapter = ArrayAdapter(
                this@CadastroAnotacaoActivity,
                android.R.layout.simple_spinner_dropdown_item,
                mutableListOf<String>("Selecione uma categoria")
            )
            spinnerCategorias.adapter = spinnerAadapter
        }
    }

    private fun inicializarObservables() {
        categoriaViewModel.categorias.observe(this){
            val listaCategorias = mutableListOf("Selecione uma categoria")
            val categorias = it.map { categoria ->
                categoria.nome
            }
            listaCategorias.addAll(categorias)
            spinnerAadapter.clear()
            spinnerAadapter.addAll(listaCategorias)
            spinnerAadapter.notifyDataSetChanged()

        }

        anotacaoViewModel.resultadoOperacao.observe(this){
            if(it.sucesso) {
                Toast.makeText(this, it.mensagem, Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, it.mensagem, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inicializarListeners() {
        with(binding) {
            btnAdicionarCategoria.setOnClickListener {
                startActivity(Intent(applicationContext, CadastroCategoriaActivity::class.java))
            }
            btnSalvarAnotacao.setOnClickListener {
                salvarAnotacao()
            }
        }
    }

    private fun salvarAnotacao() {
        with(binding) {
            val titulo = editTituloAnotacao.text.toString()
            val Descricao = editDescricaoAnotacao.text.toString()

            val posicaoSelecionada = spinnerCategorias.selectedItemPosition
            var idCategoria = if(posicaoSelecionada>0) categoriaViewModel.categorias.value?.get(posicaoSelecionada-1)?.id?:0L else 0L
            val anotacao = Anotacao(0, titulo, Descricao, idCategoria)
            anotacaoViewModel.salvar(anotacao)
        }
    }

    private fun inicializarToolbar() {
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }
}