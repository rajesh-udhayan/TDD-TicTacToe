package com.rajesh.tictactoe

class TicTacToe(row: Int) {

    private val array: Array<IntArray>  = Array(row) { IntArray(row)}

    fun getArraySize(): Int = array.size
}
