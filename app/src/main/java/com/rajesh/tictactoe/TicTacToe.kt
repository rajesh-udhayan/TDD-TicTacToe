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

    fun put(xIndex: Int, yIndex: Int, value: Int): Boolean {
        if (array[xIndex][yIndex] == -1) {
            array[xIndex][yIndex] = value
            return true
        }
        return false
    }

    fun matchHorizontally(xIndex: Int, value: Int): Boolean {
        var xCount = 1
        for (index in 0 until array.size-1){
            if (array[xIndex][index] == array[xIndex][index+1]) {
                xCount++
            }
        }
        return xCount == 3
    }
}
