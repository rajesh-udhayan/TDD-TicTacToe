package com.rajesh.tictactoe

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test


class GameActivityTest{
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldDisplayTicTacToeText(){
        composeTestRule.setContent {
            TicTacToeTheme {
                GameView()
            }
        }
    }
}