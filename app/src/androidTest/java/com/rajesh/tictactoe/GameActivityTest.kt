package com.rajesh.tictactoe

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelectable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.LiveData
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameActivityTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            TicTacToeTheme {
                GridButtons(
                    cards = listOf(
                        listOf(
                            GridCell(TicTacToe.Game.PLAYER_NONE, 0, 0),
                            GridCell(TicTacToe.Game.PLAYER_NONE, 1, 0),
                            GridCell(TicTacToe.Game.PLAYER_NONE, 2, 0)
                        ),
                        listOf(
                            GridCell(TicTacToe.Game.PLAYER_NONE, 0, 1),
                            GridCell(TicTacToe.Game.PLAYER_NONE, 1, 1),
                            GridCell(TicTacToe.Game.PLAYER_NONE, 2, 1)
                        ),
                        listOf(
                            GridCell(TicTacToe.Game.PLAYER_NONE, 0, 2),
                            GridCell(TicTacToe.Game.PLAYER_NONE, 1, 2),
                            GridCell(TicTacToe.Game.PLAYER_NONE, 2, 2)
                        )
                    )
                )
            }
        }
    }

    @Test
    fun shouldDisplayTicTacToeText() {
        with(composeTestRule) {
            val title = onNodeWithText("TicTacToe")

            title.assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplayGridCells() {
        with(composeTestRule) {
            val cellOne = onNodeWithTag("Cell 00")
            val cellTwo = onNodeWithTag("Cell 01")
            val cellThree = onNodeWithTag("Cell 02")
            val cellFour = onNodeWithTag("Cell 10")
            val cellFive = onNodeWithTag("Cell 11")
            val cellSix = onNodeWithTag("Cell 12")
            val cellSeven = onNodeWithTag("Cell 20")
            val cellEight = onNodeWithTag("Cell 21")
            val cellNine = onNodeWithTag("Cell 22")

            cellOne.assertIsDisplayed()
            cellTwo.assertIsDisplayed()
            cellThree.assertIsDisplayed()
            cellFour.assertIsDisplayed()
            cellFive.assertIsDisplayed()
            cellSix.assertIsDisplayed()
            cellSeven.assertIsDisplayed()
            cellEight.assertIsDisplayed()
            cellNine.assertIsDisplayed()
        }
    }
}