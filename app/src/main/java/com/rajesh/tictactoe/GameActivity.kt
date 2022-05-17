package com.rajesh.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class GameActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent{
                TicTacToeTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        GameView()
                    }
                }
            }
    }
}

@Composable
fun GameView() {
    Text(text = "TicTacToe")
}

@Composable
fun TicTacToeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(),
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    TicTacToeTheme {
        Column {
            GameView()
        }
    }
}