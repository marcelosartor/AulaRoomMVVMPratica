package br.com.msartor.aularoommvvmpratica.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.msartor.aularoommvvmpratica.data.database.BancoDeDados
import org.junit.After
import org.junit.Before

class AnotacaoDaoTest {

    private lateinit var bancoDeDados: BancoDeDados

    @Before
    fun setUp() {
        bancoDeDados = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BancoDeDados::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun tearDown() {
        bancoDeDados.close()
    }
}