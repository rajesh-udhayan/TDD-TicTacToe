package com.rajesh.tictactoe.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.rajesh.tictactoe.model.GameStatus
import com.rajesh.tictactoe.model.GridCell
import com.rajesh.tictactoe.model.PlayerStatus
import com.rajesh.tictactoe.R
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
                    MainView(viewModel)
                }
            }
        }
    }

}

    @Composable
    fun MainView(viewModel: GameViewModel){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                viewModel.initGridBoxes()
                            }
                        ) {
                            Icon(
                                Icons.Filled.Refresh,
                                contentDescription = "Reload Game"
                            )
                        }
                    }
                )
            }
        ) {
            GameView(viewModel)
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun GameView(viewModel: GameViewModel) {
        val cards: List<List<GridCell>>by viewModel.getBoxes().observeAsState(listOf())
        val gameStatus: LiveData<GameStatus> = viewModel.getGameStatus()
        val currentPlayer: String= if (gameStatus.value?.currentPlayer == PlayerStatus.PlayerX) "Player X" else "Player O"
        val isGameCompleted:Boolean = gameStatus.value?.isGameCompleted == true
        val winner: String= if (gameStatus.value?.winingPlayer == PlayerStatus.PlayerX) "Player X" else "Player O"

        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GameContentView(cards,currentPlayer,isGameCompleted,winner){
                if (isGameCompleted) {
                    viewModel.initGridBoxes()
                } else {
                    viewModel.selectBox(it)
                }
            }
        }
    }

    @Composable
    fun GameContentView(cards: List<List<GridCell>>,
                        currentPlayer:String,
                        isGameCompleted: Boolean,
                        winner: String,
                        boxSelected: (card: GridCell) -> Unit = {}) {
        Text(text = "TicTacToe",
            style = Typography().h3,
            modifier = Modifier.padding(20.dp))

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
                        GridCell(card) {
                            boxSelected(card)
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                horizontalArrangement = Arrangement.Center,
            ) {
                if (isGameCompleted) {
                    Text(
                        text = "Winner: $winner",
                        fontSize = 28.sp,
                        color = Color.Black,
                    )
                } else {
                    Text(
                        text = "Current: $currentPlayer",
                        fontSize = 28.sp,
                        color = Color.Black,
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun GridCell(card: GridCell, selectBox: () -> Unit = {}) {
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
