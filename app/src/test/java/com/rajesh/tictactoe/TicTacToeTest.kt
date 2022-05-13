package com.rajesh.tictactoe

import com.google.common.truth.Truth.*
import com.rajesh.tictactoe.TicTacToe.Game.*
import org.junit.Before
import org.junit.Test

class TicTacToeTest {

    private lateinit var ticTacToe: TicTacToe
    private val rowCount = 3

    @Before
    fun setUp(){
        ticTacToe = TicTacToe(rowCount)
        ticTacToe.initGame()
    }

    @Test
    fun `should create an two dimensional array when row count provided`(){
        assertThat(ticTacToe.getArray().size).isEqualTo(rowCount)
    }

    @Test
    fun `should fill all cells with -1 when game initialized`(){
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

        ticTacToe.put(xIndex,yIndex,value)

        val isAdded = ticTacToe.put(xIndex,yIndex,newValue)

        assertThat(ticTacToe.getArray()[xIndex][yIndex]).isNotEqualTo(newValue)
        assertThat(isAdded).isFalse()
    }

    @Test
    fun `should return true when horizontal cells have same value`(){
        val xIndex = 0

        ticTacToe.put(xIndex,0,PLAYER_O.value)
        ticTacToe.put(xIndex,1,PLAYER_O.value)
        ticTacToe.put(xIndex,2,PLAYER_O.value)
        val matchFound = ticTacToe.match(xIndex,0, PLAYER_O.value)

        assertThat(matchFound).isTrue()
    }

    @Test
    fun `should return false when horizontal cells doesn't have same value`(){
        val xIndex = 0

        ticTacToe.put(xIndex,0,PLAYER_O.value)
        ticTacToe.put(xIndex,1,PLAYER_X.value)
        ticTacToe.put(xIndex,2,PLAYER_O.value)
        val matchFound = ticTacToe.match(xIndex,0, PLAYER_O.value)

        assertThat(matchFound).isFalse()
    }

    @Test
    fun `should return true when vertical cells have same value`(){
        val yIndex = 1

        ticTacToe.put(0,yIndex, PLAYER_X.value)
        ticTacToe.put(1,yIndex, PLAYER_X.value)
        ticTacToe.put(2,yIndex, PLAYER_X.value)
        val matchFound = ticTacToe.match(0,yIndex,PLAYER_X.value)

        assertThat(matchFound).isTrue()
    }

    @Test
    fun `should return false when vertical cells have doesn't same value`(){
        val yIndex = 1

        ticTacToe.put(0,yIndex, PLAYER_X.value)
        ticTacToe.put(1,yIndex, PLAYER_O.value)
        ticTacToe.put(2,yIndex, PLAYER_X.value)
        val matchFound = ticTacToe.match(0,yIndex,PLAYER_X.value)

        assertThat(matchFound).isFalse()
    }

    @Test
    fun `should return true when right diagonal cells have same value`(){
        ticTacToe.put(0,0,PLAYER_O.value)
        ticTacToe.put(1,1,PLAYER_O.value)
        ticTacToe.put(2,2,PLAYER_O.value)
        val matchFound = ticTacToe.match(0,0,PLAYER_O.value)

        assertThat(matchFound).isTrue()
    }

    @Test
    fun `should return false when right diagonal cells doesn't have same value`(){
        ticTacToe.put(0,0,PLAYER_O.value)
        ticTacToe.put(1,1,PLAYER_X.value)
        ticTacToe.put(2,2,PLAYER_O.value)
        val matchFound = ticTacToe.match(0,0,PLAYER_O.value)

        assertThat(matchFound).isFalse()
    }

    @Test
    fun `should return false when all right diagonal values are -1`(){
        val matchFound = ticTacToe.match(0,0,PLAYER_NONE.value)

        assertThat(matchFound).isFalse()
    }

    @Test
    fun `should return true when left diagonal cells have same value`(){
        ticTacToe.put(0,2,PLAYER_O.value)
        ticTacToe.put(1,1,PLAYER_O.value)
        ticTacToe.put(2,0,PLAYER_O.value)
        val matchFound = ticTacToe.match(0,0,PLAYER_O.value)

        assertThat(matchFound).isTrue()
    }

    @Test
    fun `should return false when left diagonal cells doesn't have same value`(){
        ticTacToe.put(0,2,PLAYER_O.value)
        ticTacToe.put(1,1,PLAYER_X.value)
        ticTacToe.put(2,0,PLAYER_O.value)
        val matchFound = ticTacToe.match(0,0,PLAYER_O.value)

        assertThat(matchFound).isFalse()
    }

    @Test
    fun `should return false when all left diagonal cells are -1`(){
        val matchFound = ticTacToe.match(0,0,PLAYER_NONE.value)

        assertThat(matchFound).isFalse()
    }

    @Test
    fun `should return playerO as winner when value any match found for 1`(){
        ticTacToe.put(0,0,PLAYER_O.value)
        ticTacToe.put(0,1,PLAYER_O.value)
        ticTacToe.put(0,2,PLAYER_O.value)
        ticTacToe.match(0,0,PLAYER_O.value)

        assertThat(ticTacToe.getWinner()).isEqualTo(PLAYER_O.value)
    }
}