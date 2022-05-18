package com.rajesh.tictactoe

class GridCell(
    var status: TicTacToe.Game = TicTacToe.Game.PLAYER_NONE,
    var columnIndex: Int = 0,
    var rowIndex: Int = 0
) {

    fun showPlayer(): String {
        return when (status) {
            TicTacToe.Game.PLAYER_X -> "X"
            TicTacToe.Game.PLAYER_O -> "O"
            TicTacToe.Game.PLAYER_NONE -> ""
        }
    }
}