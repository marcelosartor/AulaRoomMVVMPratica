package br.com.msartor.aularoommvvmpratica.di

import android.content.Context
import br.com.msartor.aularoommvvmpratica.data.dao.AnotacaoDao
import br.com.msartor.aularoommvvmpratica.data.dao.CategoriaDao
import br.com.msartor.aularoommvvmpratica.data.database.BancoDeDados
import br.com.msartor.aularoommvvmpratica.data.repository.AnotacaoRepository
import br.com.msartor.aularoommvvmpratica.data.repository.AnotacaoRepositoryImpl
import br.com.msartor.aularoommvvmpratica.data.repository.CategoriaRepository
import br.com.msartor.aularoommvvmpratica.data.repository.CategoriaRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providerBancoDeDados(@ApplicationContext context: Context): BancoDeDados {
        return BancoDeDados.getInstance(context)
    }

    @Provides
    fun providerCategoriaDao(bancoDeDados: BancoDeDados): CategoriaDao {
        return bancoDeDados.categoriaDao
    }

    @Provides
    fun providerCategoriaRepository(categoriaDao: CategoriaDao): CategoriaRepository {
        return CategoriaRepositoryImpl(categoriaDao)
    }

    @Provides
    fun providerAnotacaoDao(bancoDeDados: BancoDeDados): AnotacaoDao {
        return bancoDeDados.anotacaoDao
    }

    @Provides
    fun providerAnotacaoRepository(anotacaoDao: AnotacaoDao): AnotacaoRepository {
        return AnotacaoRepositoryImpl(anotacaoDao)
    }


}