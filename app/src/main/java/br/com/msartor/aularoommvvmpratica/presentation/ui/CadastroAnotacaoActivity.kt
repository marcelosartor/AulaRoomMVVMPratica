package br.com.msartor.aularoommvvmpratica.presentation.ui

import android.content.Intent
import android.os.Build
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

    private var anotacao:Anotacao? = null

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

            val bundle = intent.extras
            if (bundle != null) {
                anotacao = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    bundle.getParcelable("anotacao", Anotacao::class.java)
                }else{
                    bundle.getParcelable<Anotacao>("anotacao")
                }
                if(anotacao!=null){
                    binding.editTituloAnotacao.setText(anotacao!!.titulo)
                    binding.editDescricaoAnotacao.setText(anotacao!!.descricao)
                }
            }
            spinnerAadapter = ArrayAdapter(
                this@CadastroAnotacaoActivity,
                android.R.layout.simple_spinner_dropdown_item,
                mutableListOf<String>("Selecione uma categoria")
            )
            spinnerCategorias.adapter = spinnerAadapter
        }
    }

    private fun inicializarObservables() {
        categoriaViewModel.categorias.observe(this){ it ->
            val listaCategorias = mutableListOf("Selecione uma categoria")
            val categorias = it.map { categoria ->
                categoria.nome
            }
            listaCategorias.addAll(categorias)
            spinnerAadapter.clear()
            spinnerAadapter.addAll(listaCategorias)
            spinnerAadapter.notifyDataSetChanged()

            var posicaoCategoria = 0
            if(anotacao!=null){
                posicaoCategoria = it.indexOfFirst { item->item.id==anotacao!!.categoriaId } + 1

            }
            binding.spinnerCategorias.setSelection(posicaoCategoria)

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

            var idAnotacao = 0L
            if(anotacao!=null){
                idAnotacao = anotacao!!.id
            }
            anotacao = Anotacao(idAnotacao, titulo, Descricao, idCategoria)
            anotacaoViewModel.salvar(anotacao!!)
        }
    }

    private fun inicializarToolbar() {
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }
}