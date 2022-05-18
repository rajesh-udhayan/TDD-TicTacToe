package com.rajesh.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(val ticTacToe: TicTacToe) : ViewModel() {

    private val gameStatus: MutableLiveData<GameStatus> by lazy {
        MutableLiveData<GameStatus>()
    }

    private val boxes: MutableLiveData<MutableList<MutableList<GridCell>>> by lazy {
        MutableLiveData<MutableList<MutableList<GridCell>>>()
    }

    fun getGameStatus(): LiveData<GameStatus> {
        return gameStatus
    }

    fun getBoxes(): LiveData<MutableList<MutableList<GridCell>>> {
        return boxes
    }

    fun initGridBoxes() {
        var columnIndex: Int = 0
        var rowIndex: Int = 0

        ticTacToe.initGame()

        boxes.value = MutableList(3) {
            rowIndex = 0

            MutableList(3) {
                GridCell(
                    columnIndex = columnIndex++ / 3,
                    rowIndex = rowIndex++
                )
            }
        }
    }

    fun selectBox(card: GridCell) {

    }
}