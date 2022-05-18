package com.rajesh.tictactoe

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    private const val rowCount = 3

    @Singleton
    @Provides
    fun provideTicTacToe() = TicTacToe(rowCount)
}