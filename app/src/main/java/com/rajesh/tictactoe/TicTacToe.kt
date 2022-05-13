package com.rajesh.tictactoe

class TicTacToe(row: Int) {

    private val array: Array<IntArray> = Array(row) { IntArray(row) }
    private val cell = row
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

    fun match(xIndex: Int, yIndex: Int) : Boolean {
        var xCount = 1; var yCount = 1; var rightCount = 1; var leftCount = 1
        var length = array.size-1

        for (index in 0 until array.size-1){
            if (array[xIndex][index+1] != -1 && array[xIndex][index] == array[xIndex][index+1]) {
                xCount++
            }

            if (array[index+1][yIndex] != -1 && array[index][yIndex] == array[index+1][yIndex]){
                yCount++
            }

            if (array[index+1][index+1] != -1 && array[index][index] == array[index+1][index+1]){
                rightCount++
            }
            if (array[index+1][length-1] != -1 && array[index][length] == array[index+1][length-1]){
                leftCount++
            }

            if (xCount == cell || yCount == cell || rightCount == cell || leftCount == cell){
                return true
            }
            length--
        }
        return false
    }
}
