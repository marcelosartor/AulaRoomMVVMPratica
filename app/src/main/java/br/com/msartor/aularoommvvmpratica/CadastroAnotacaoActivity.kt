package br.com.msartor.aularoommvvmpratica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.msartor.aularoommvvmpratica.databinding.ActivityCadastroAnotacaoBinding

class CadastroAnotacaoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCadastroAnotacaoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        Log.d("MainActivity", "btnAdicionarCategoria: ${binding.btnAdicionarCategoria}")
        binding.btnAdicionarCategoria.setOnClickListener {
            startActivity(Intent(this, CadastroCategoriaActivity::class.java))
        }

    }
}