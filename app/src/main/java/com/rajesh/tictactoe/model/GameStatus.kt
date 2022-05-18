package com.rajesh.tictactoe.model

class GameStatus(
    var currentPlayer: PlayerStatus = PlayerStatus.PlayerX,
    var winingPlayer: PlayerStatus = PlayerStatus.Empty,
    var isGameCompleted: Boolean = false,
) {}