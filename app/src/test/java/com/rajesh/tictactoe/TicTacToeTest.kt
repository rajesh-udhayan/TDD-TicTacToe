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

        //Randomly verify anyone cell value
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

    @Test
    fun `should add and return true only when value provided for index is -1`(){
        val xIndex = 0
        val yIndex = 1
        val value = 1

        ticTacToe.initGame()
        val isAdded = ticTacToe.put(xIndex,yIndex,value)

        assertThat(ticTacToe.getArray()[xIndex][yIndex]).isEqualTo(value)
        assertThat(isAdded).isTrue()
    }

    @Test
    fun `should not add and return false when value provided for index is not -1`(){
        val xIndex = 0
        val yIndex = 1
        val value = 1
        val newValue = 0

        ticTacToe.initGame()
        ticTacToe.put(xIndex,yIndex,value)

        val isAdded = ticTacToe.put(xIndex,yIndex,newValue)

        assertThat(ticTacToe.getArray()[xIndex][yIndex]).isNotEqualTo(newValue)
        assertThat(isAdded).isFalse()
    }

    @Test
    fun `should return true when horizontal cells have same value`(){
        val xIndex = 0

        ticTacToe.initGame()
        ticTacToe.put(xIndex,0,1)
        ticTacToe.put(xIndex,1,1)
        ticTacToe.put(xIndex,2,1)
        val matchFound = ticTacToe.matchHorizontally(xIndex,1)

        assertThat(matchFound).isTrue()
    }

    @Test
    fun `should return false when horizontal cells doesn't have same value`(){
        val xIndex = 0

        ticTacToe.initGame()
        ticTacToe.put(xIndex,0,1)
        ticTacToe.put(xIndex,1,0)
        ticTacToe.put(xIndex,2,1)
        val matchFound = ticTacToe.matchHorizontally(xIndex,1)

        assertThat(matchFound).isFalse()
    }
}