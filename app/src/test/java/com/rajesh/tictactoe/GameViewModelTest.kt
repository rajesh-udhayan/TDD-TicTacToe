package com.rajesh.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class GameViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun shouldReturnListOfGridCellsWhenInitGridBoxesCalled() {
        val ticTacToe = TicTacToe(3)
        val viewModel = GameViewModel(ticTacToe)

        viewModel.initGridBoxes()

        assertThat(viewModel.getBoxes().getOrAwaitValue())
            .isNotEmpty()
    }
}