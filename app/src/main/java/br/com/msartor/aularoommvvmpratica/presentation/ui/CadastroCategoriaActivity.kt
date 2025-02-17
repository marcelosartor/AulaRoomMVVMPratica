package br.com.msartor.aularoommvvmpratica.presentation.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.msartor.aularoommvvmpratica.R
import br.com.msartor.aularoommvvmpratica.data.entity.Categoria
import br.com.msartor.aularoommvvmpratica.databinding.ActivityCadastroCategoriaBinding
import br.com.msartor.aularoommvvmpratica.presentation.viewmodel.CategoriaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroCategoriaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroCategoriaBinding.inflate(layoutInflater)
    }
    private val categoriViewModel: CategoriaViewModel by viewModels()

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
        inicializarListeners()
        inicializarObservables()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Fecha a Activity e volta para a anterior
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun inicializarToolbar() {
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun inicializarObservables() {
        categoriViewModel.resultadoOperacao.observe(this){
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
            btnSalvarCategoria.setOnClickListener {
                salvarCategoria()
            }
        }
    }

    private fun ActivityCadastroCategoriaBinding.salvarCategoria() {
        val nomeCategoria = editNomeCategoria.text.toString()
        if (nomeCategoria.isNotBlank()) {
            val categoria = Categoria(0, nome = nomeCategoria)
            categoriViewModel.salvar(categoria)
        } else {
            Toast.makeText(
                applicationContext,
                "Preencha o campo nome da Categoria",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}