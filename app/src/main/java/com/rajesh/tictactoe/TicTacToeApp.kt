package com.rajesh.tictactoe

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TicTacToeApp: MultiDexApplication() {
}