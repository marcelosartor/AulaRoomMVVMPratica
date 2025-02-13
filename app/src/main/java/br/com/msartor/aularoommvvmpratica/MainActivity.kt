package br.com.msartor.aularoommvvmpratica

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import br.com.msartor.aularoommvvmpratica.databinding.ActivityMainBinding
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuHost
import androidx.lifecycle.Lifecycle
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
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

        inicializarBarraNavegacao()
        inicializarEventosDeClique()

    }

    private fun inicializarEventosDeClique() {
        binding.fabAdicionar.setOnClickListener {
            startActivity(Intent(this,CadastroAnotacaoActivity::class.java))
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
                        return true
                    }

                })

                /*
                searchView.post {
                    val textView = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
                    textView.setTextColor(Color.WHITE)  // Texto branco
                    textView.setHintTextColor(Color.LTGRAY)  // Placeholder cinza claro

                    val searchCloseButton = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
                    val searchMagIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
                    val searchGoButton = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_go_btn)
                    val searchBackIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_button)

                    searchCloseButton?.setColorFilter(Color.WHITE)
                    searchMagIcon?.setColorFilter(Color.WHITE)
                    searchGoButton?.setColorFilter(Color.WHITE)
                    searchBackIcon?.setColorFilter(Color.WHITE)

                    // Remover fundo branco do SearchView
                    searchView.background = null
                }
                 */


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