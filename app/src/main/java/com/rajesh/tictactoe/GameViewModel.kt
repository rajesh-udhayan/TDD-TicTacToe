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

        gameStatus.value = GameStatus()
    }

    fun selectBox(card: GridCell) {
        val currentPlayer = gameStatus.value?.currentPlayer!!
        var isModified = false

        val updatedList: MutableList<MutableList<GridCell>> = boxes.value?.map { columns ->
            val newColumns = columns.map { row ->
                if (card.columnIndex == row.columnIndex && card.rowIndex == row.rowIndex) {
                    if (row.status == PlayerStatus.Empty) {
                        row.status = currentPlayer
                        isModified = true
                    }
                }
                row
            }
            newColumns
        } as MutableList<MutableList<GridCell>>

        if (isModified) {
            gameStatus.value?.currentPlayer = gameStatus.value?.currentPlayer!!.next()
            boxes.value?.removeAll { true }
            boxes.value = updatedList
        }

        checkWinnerStatus()
    }

    private fun checkWinnerStatus(){
        //For vertical cells
        (0..2).forEach { index ->
            if (
                boxes.value?.get(index = index)?.get(0)!!.status == boxes.value?.get(index = index)?.get(1)!!.status &&
                boxes.value?.get(index = index)?.get(1)!!.status == boxes.value?.get(index = index)?.get(2)!!.status &&
                boxes.value?.get(index = index)?.get(2)!!.status != PlayerStatus.Empty
            ) {
                gameStatus.value?.isGameCompleted = true
                gameStatus.value?.winingPlayer = boxes.value?.get(index = index)?.get(2)!!.status
            }
        }
    }
}