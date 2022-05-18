package com.rajesh.tictactoe

import com.rajesh.tictactoe.PlayerStatus

class GameStatus(
    var currentPlayer: PlayerStatus = PlayerStatus.PlayerX,
    var winingPlayer: PlayerStatus = PlayerStatus.Empty,
    var isGameCompleted: Boolean = false,
) {}