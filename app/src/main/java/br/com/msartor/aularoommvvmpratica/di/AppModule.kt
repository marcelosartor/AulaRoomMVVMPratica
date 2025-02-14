package br.com.msartor.aularoommvvmpratica.di

import android.content.Context
import br.com.msartor.aularoommvvmpratica.data.database.BancoDeDados
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
}