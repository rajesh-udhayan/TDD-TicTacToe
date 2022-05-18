package com.rajesh.tictactoe

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.rajesh.tictactoe.game.GameContentView
import com.rajesh.tictactoe.game.TicTacToeTheme
import com.rajesh.tictactoe.model.GridCell
import com.rajesh.tictactoe.model.PlayerStatus
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
                GameContentView(
                    cards = listOf(
                        listOf(
                            GridCell(PlayerStatus.Empty, 0, 0),
                            GridCell(PlayerStatus.Empty, 1, 0),
                            GridCell(PlayerStatus.Empty, 2, 0)
                        ),
                        listOf(
                            GridCell(PlayerStatus.Empty, 0, 1),
                            GridCell(PlayerStatus.Empty, 1, 1),
                            GridCell(PlayerStatus.Empty, 2, 1)
                        ),
                        listOf(
                            GridCell(PlayerStatus.Empty, 0, 2),
                            GridCell(PlayerStatus.Empty, 1, 2),
                            GridCell(PlayerStatus.Empty, 2, 2)
                        )
                    ),
                    "",false,""
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