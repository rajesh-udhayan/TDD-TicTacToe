package com.rajesh.tictactoe

class GridCell(
    var status: PlayerStatus = PlayerStatus.Empty,
    var columnIndex: Int = 0,
    var rowIndex: Int = 0
) {

    fun showPlayer(): String {
        return when (status) {
            PlayerStatus.PlayerX -> "X"
            PlayerStatus.PlayerO -> "O"
            PlayerStatus.Empty -> ""
        }
    }
}