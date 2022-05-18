package com.rajesh.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.rajesh.tictactoe.game.GameViewModel
import com.rajesh.tictactoe.model.GridCell
import com.rajesh.tictactoe.model.PlayerStatus
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class GameViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: GameViewModel

    @Before
    fun setUp(){
        viewModel = GameViewModel()

        viewModel.initGridBoxes()
    }

    @Test
    fun shouldReturnListOfGridCellsWhenInitGridBoxesCalled() {
        assertThat(viewModel.getBoxes().getOrAwaitValue())
            .isNotEmpty()
    }

    @Test
    fun shouldUpdateBoxStatusAsPlayerXValueWhenSelectedForFirstTime(){
        val gridCell: GridCell = viewModel.getBoxes().getOrAwaitValue()[0][0]

        viewModel.selectBox(gridCell)

        assertThat(viewModel.getBoxes().getOrAwaitValue()[0][0].status).isEqualTo(PlayerStatus.PlayerX)
    }

    @Test
    fun shouldUpdateFirstBoxStatusAsPlayerXAndSencondBosStatusAsPlayerOWhenTwoBoxSelected(){
        val gridCell1: GridCell = viewModel.getBoxes().getOrAwaitValue()[0][0]
        val gridCell2: GridCell = viewModel.getBoxes().getOrAwaitValue()[0][1]

        viewModel.selectBox(gridCell1)
        viewModel.selectBox(gridCell2)

        assertThat(viewModel.getBoxes().getOrAwaitValue()[0][0].status).isEqualTo(PlayerStatus.PlayerX)
        assertThat(viewModel.getBoxes().getOrAwaitValue()[0][1].status).isEqualTo(PlayerStatus.PlayerO)
    }

    @Test
    fun shouldReturnPlayerAsWinnerWhenVerticalCellsHaveSameValue(){
        val gridCell1: GridCell = viewModel.getBoxes().getOrAwaitValue()[0][0]
        val gridCell2: GridCell = viewModel.getBoxes().getOrAwaitValue()[1][0]
        val gridCell3: GridCell = viewModel.getBoxes().getOrAwaitValue()[0][1]
        val gridCell4: GridCell = viewModel.getBoxes().getOrAwaitValue()[1][1]
        val gridCell5: GridCell = viewModel.getBoxes().getOrAwaitValue()[0][2]

        viewModel.selectBox(gridCell1)
        viewModel.selectBox(gridCell2)
        viewModel.selectBox(gridCell3)
        viewModel.selectBox(gridCell4)
        viewModel.selectBox(gridCell5)

        assertThat(viewModel.getGameStatus().getOrAwaitValue().winingPlayer).isEqualTo(PlayerStatus.PlayerX)
    }

    @Test
    fun shouldReturnPlayerAsWinnerWhenHorizontalCellsHaveSameValue(){
        val gridCell1: GridCell = viewModel.getBoxes().getOrAwaitValue()[0][0]
        val gridCell2: GridCell = viewModel.getBoxes().getOrAwaitValue()[0][1]
        val gridCell3: GridCell = viewModel.getBoxes().getOrAwaitValue()[1][0]
        val gridCell4: GridCell = viewModel.getBoxes().getOrAwaitValue()[1][1]
        val gridCell5: GridCell = viewModel.getBoxes().getOrAwaitValue()[2][0]

        viewModel.selectBox(gridCell1)
        viewModel.selectBox(gridCell2)
        viewModel.selectBox(gridCell3)
        viewModel.selectBox(gridCell4)
        viewModel.selectBox(gridCell5)

        assertThat(viewModel.getGameStatus().getOrAwaitValue().winingPlayer).isEqualTo(PlayerStatus.PlayerX)
    }

    @Test
    fun shouldReturnPlayerAsWinnerWhenRightDiagonalCellsHaveSameValue(){
        val gridCell1: GridCell = viewModel.getBoxes().getOrAwaitValue()[0][0]
        val gridCell2: GridCell = viewModel.getBoxes().getOrAwaitValue()[0][1]
        val gridCell3: GridCell = viewModel.getBoxes().getOrAwaitValue()[1][1]
        val gridCell4: GridCell = viewModel.getBoxes().getOrAwaitValue()[1][0]
        val gridCell5: GridCell = viewModel.getBoxes().getOrAwaitValue()[2][2]

        viewModel.selectBox(gridCell1)
        viewModel.selectBox(gridCell2)
        viewModel.selectBox(gridCell3)
        viewModel.selectBox(gridCell4)
        viewModel.selectBox(gridCell5)

        assertThat(viewModel.getGameStatus().getOrAwaitValue().winingPlayer).isEqualTo(PlayerStatus.PlayerX)
    }

    @Test
    fun shouldReturnPlayerAsWinnerWhenLeftDiagonalCellsHaveSameValue(){
        val gridCell1: GridCell = viewModel.getBoxes().getOrAwaitValue()[0][2]
        val gridCell2: GridCell = viewModel.getBoxes().getOrAwaitValue()[0][1]
        val gridCell3: GridCell = viewModel.getBoxes().getOrAwaitValue()[1][1]
        val gridCell4: GridCell = viewModel.getBoxes().getOrAwaitValue()[1][0]
        val gridCell5: GridCell = viewModel.getBoxes().getOrAwaitValue()[2][0]

        viewModel.selectBox(gridCell1)
        viewModel.selectBox(gridCell2)
        viewModel.selectBox(gridCell3)
        viewModel.selectBox(gridCell4)
        viewModel.selectBox(gridCell5)

        assertThat(viewModel.getGameStatus().getOrAwaitValue().winingPlayer).isEqualTo(PlayerStatus.PlayerX)
    }
}