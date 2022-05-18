package com.rajesh.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GameActivity : ComponentActivity() {

    private val viewModel by viewModels<GameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initGridBoxes()
        setContent {
            TicTacToeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    GameView(viewModel)
                }
            }
        }
    }

}

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun GameView(viewModel: GameViewModel) {
        val cards: List<List<GridCell>>by viewModel.getBoxes().observeAsState(listOf())
        Column {
            GridButtons(cards){
                viewModel.selectBox(it)
            }
        }
    }

    @Composable
    fun GridButtons( cards: List<List<GridCell>>, boxSelected: (card: GridCell) -> Unit = {}) {
        Text(text = "TicTacToe")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.Center,
        ) {
            cards.forEach { rows ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    rows.forEach { card ->
                        ActionButton(card) {
                            boxSelected(card)
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ActionButton(card: GridCell, selectBox: () -> Unit = {}) {
        val indexTag = "Cell ${card.rowIndex}${card.columnIndex}"
        Card(
            modifier = Modifier
                .padding(all = 10.dp)
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(5.dp),
                )
                .height(100.dp)
                .width(100.dp).
            testTag(indexTag),
            backgroundColor = Color.White,
            onClick = selectBox
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = card.showPlayer(),
                    fontSize = 34.sp,
                    color = Color.Black,
                )
            }
        }
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
