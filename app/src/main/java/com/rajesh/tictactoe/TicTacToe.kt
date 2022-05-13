package com.rajesh.tictactoe

class TicTacToe(row: Int) {

    private val array: Array<IntArray> = Array(row) { IntArray(row) }

    fun getArray() = array

    fun initGame() {
        array.forEachIndexed { xIndex, _ ->
            array.forEachIndexed { yIndex, _ ->
                array[xIndex][yIndex] = -1
            }
        }
    }
}
