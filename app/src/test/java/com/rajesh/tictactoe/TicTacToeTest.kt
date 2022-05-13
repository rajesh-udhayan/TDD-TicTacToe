package com.rajesh.tictactoe

import com.google.common.truth.Truth.*
import org.junit.Before
import org.junit.Test

class TicTacToeTest {

    private lateinit var ticTacToe: TicTacToe
    private val rowCount = 3

    @Before
    fun setUp(){
        ticTacToe = TicTacToe(rowCount)
    }

    @Test
    fun `should create an two dimensional array when row count provided`(){
        assertThat(ticTacToe.getArray().size).isEqualTo(rowCount)
    }

    @Test
    fun `should fill all cells with -1 when game initialized`(){
        ticTacToe.initGame()

        //Randomly test anyone cell value
        assertThat(ticTacToe.getArray()[0][2]).isEqualTo(-1)
    }

    @Test
    fun `should fill the cell with value when index and value provided`(){
        val xIndex = 1
        val yIndex = 2
        val value = 0

        ticTacToe.put(xIndex,yIndex,value)

        assertThat(ticTacToe.getArray()[xIndex][yIndex]).isEqualTo(value)
    }
}