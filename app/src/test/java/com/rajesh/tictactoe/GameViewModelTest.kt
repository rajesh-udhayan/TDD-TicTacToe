package com.rajesh.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
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
        val ticTacToe = TicTacToe(3)
        viewModel = GameViewModel(ticTacToe)

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
}