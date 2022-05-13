package com.rajesh.tictactoe

import com.google.common.truth.Truth.*
import org.junit.Test

class TicTacToeTest {

    @Test
    fun `should create an two dimensional array when row count provided`(){
        val rowCount = 3
        val ticTacToe = TicTacToe(rowCount)

        assertThat(ticTacToe.getArraySize()).isEqualTo(rowCount)
    }
}