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

    fun matchHorizontally(xIndex: Int): Boolean {
        var xCount = 1
        for (index in 0 until array.size-1){
            if (array[xIndex][index] == array[xIndex][index+1]) {
                xCount++
            }
        }
        return xCount == 3
    }

    fun matchVertically(yIndex: Int): Boolean {
        var yCount = 1
        for (index in 0 until array.size-1){
            if (array[index][yIndex] == array[index+1][yIndex]){
                yCount++
            }
        }
        return yCount == 3
    }

    fun matchRightDiagonally(): Boolean {
        var rightCount = 1
        for(index in 0 until array.size-1){
            if (array[index+1][index+1] != -1 && array[index][index] == array[index+1][index+1]){
                rightCount++
            }
        }
        return rightCount == 3
    }

    fun matchLeftDiagonally(): Boolean {
        return true
    }
}
