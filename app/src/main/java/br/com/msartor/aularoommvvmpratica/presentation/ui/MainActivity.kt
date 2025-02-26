package br.com.msartor.aularoommvvmpratica.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.msartor.aularoommvvmpratica.databinding.ActivityMainBinding
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuHost
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.msartor.aularoommvvmpratica.R
import br.com.msartor.aularoommvvmpratica.data.database.BancoDeDados
import br.com.msartor.aularoommvvmpratica.data.entity.Anotacao
import br.com.msartor.aularoommvvmpratica.data.entity.Categoria
import br.com.msartor.aularoommvvmpratica.presentation.ui.adapter.AnotacaoAdapter
import br.com.msartor.aularoommvvmpratica.presentation.viewmodel.AnotacaoViewModel
import br.com.msartor.aularoommvvmpratica.presentation.viewmodel.CategoriaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val anotacaoViewModel: AnotacaoViewModel by viewModels()

    private lateinit var anotacaoAdapter: AnotacaoAdapter;


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar: Toolbar = binding.toolbar //findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        inicializarUI()
        inicializarEventosDeClique()
        inicalizarObservables()
    }


    override fun onStart() {
        super.onStart()
        anotacaoViewModel.listarAnotacaoECategoria()
    }

    private fun inicalizarObservables() {
        anotacaoViewModel.anotacoesECategoria.observe(this) {
            anotacaoAdapter.configurarLista(it)
        }
        anotacaoViewModel.resultadoOperacao.observe(this) {
            if(it.sucesso) {
                Toast.makeText(this, it.mensagem, Toast.LENGTH_SHORT).show()
                anotacaoViewModel.listarAnotacaoECategoria()
            }else{
                Toast.makeText(this, it.mensagem, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun inicializarUI() {
        val onClickRemover = { anotacao:Anotacao->
            anotacaoViewModel.remover(anotacao)
        }
        val onclikAtualizar = { anotacao:Anotacao->
            val intent = Intent(this, CadastroAnotacaoActivity::class.java)
            intent.putExtra("anotacao",anotacao)
            startActivity(intent)
        }
        with(binding){
            anotacaoAdapter = AnotacaoAdapter(onClickRemover,onclikAtualizar)
            rvAnotacoes.adapter = anotacaoAdapter
            rvAnotacoes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }
        inicializarBarraNavegacao()
    }

    private fun inicializarEventosDeClique() {
        binding.fabAdicionar.setOnClickListener {
            startActivity(Intent(this, CadastroAnotacaoActivity::class.java))
        }
    }

    private fun inicializarBarraNavegacao() {
        val menuHost: MenuHost = this

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_principal, menu)


                val itemPesquisa = menu.findItem(R.id.item_pesquisa)
                //itemPesquisa.actionView?.context?.setTheme(com.google.android.material.R.style.ThemeOverlay_Material3_Dark)

                val searchView = itemPesquisa.actionView as SearchView
                searchView.queryHint = "Digite algo para pesquisar"
                searchView.setOnClickListener{
                    Log.i("pesquisa_search","Sai do SerachView")
                    true
                }
                searchView.setOnQueryTextListener( object : OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        Log.i("pesquisa_search","onQueryTextSubmit: ${query}")
                        searchView.clearFocus()
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        Log.i("pesquisa_search","onQueryTextChange: ${newText}")
                        if(newText != null)
                            anotacaoViewModel.pesquisarAnotacaoECategoria(newText ?: "")
                        return true
                    }

                })



            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.item_pesquisa -> {
                        true
                    }
                    else -> false
                }
            }
        }, this, Lifecycle.State.RESUMED)


    }
}