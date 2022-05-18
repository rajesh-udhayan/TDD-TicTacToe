package com.rajesh.tictactoe.model

enum class PlayerStatus {
    Empty,
    PlayerX,
    PlayerO;

    fun next(): PlayerStatus {
        if (this.ordinal == PlayerX.ordinal) {
            return PlayerO
        }

        return PlayerX
    }
}