package com.rajesh.tictactoe.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rajesh.tictactoe.model.GameStatus
import com.rajesh.tictactoe.model.GridCell
import com.rajesh.tictactoe.model.PlayerStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(): ViewModel() {

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

        if (isModified && gameStatus.value?.isGameCompleted == false) {
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

        //For horizontal cells
        (0..2).forEach() { index ->
            if (
                boxes.value?.get(0)?.get(index = index)!!.status == boxes.value?.get(1)?.get(index = index)!!.status &&
                boxes.value?.get(1)?.get(index = index)!!.status == boxes.value?.get(2)?.get(index = index)!!.status &&
                boxes.value?.get(2)?.get(index = index)!!.status != PlayerStatus.Empty
            ) {
                gameStatus.value?.isGameCompleted = true
                gameStatus.value?.winingPlayer = boxes.value?.get(2)?.get(index = index)!!.status
            }
        }

        //For Diagonal cells
        if (
            boxes.value?.get(0)?.get(0)!!.status == boxes.value?.get(1)?.get(1)!!.status &&
            boxes.value?.get(1)?.get(1)!!.status == boxes.value?.get(2)?.get(2)!!.status &&
            boxes.value?.get(2)?.get(2)!!.status != PlayerStatus.Empty
        ) {
            gameStatus.value?.isGameCompleted = true
            gameStatus.value?.winingPlayer = boxes.value?.get(1)?.get(1)!!.status
        }

        if (
            boxes.value?.get(0)?.get(2)!!.status == boxes.value?.get(1)?.get(1)!!.status &&
            boxes.value?.get(1)?.get(1)!!.status == boxes.value?.get(2)?.get(0)!!.status &&
            boxes.value?.get(2)?.get(0)!!.status != PlayerStatus.Empty
        ) {
            gameStatus.value?.isGameCompleted = true
            gameStatus.value?.winingPlayer = boxes.value?.get(1)?.get(1)!!.status
        }
    }
}