package com.example.oneononechess.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oneononechess.model.ChessBoardApi
import com.example.oneononechess.model.entity.BoardModel
import com.example.oneononechess.model.entity.ChessPiece

class ChessBoardViewModel(private val boardApi: ChessBoardApi) : ViewModel() {

    private var livedata = MutableLiveData<BoardModel>()
    fun getLiveData(): LiveData<BoardModel> = livedata

    fun getChessBoard() = getChessBoardFromLocalStorage()

    private fun getChessBoardFromLocalStorage() {
        val board = boardApi.getChessBoard()
        livedata.postValue(board)
    }

    fun getChessBoardAfterMove(movingPiece: ChessPiece, toRow: Int, toCol: Int) {
        boardApi.regularMove(movingPiece, toRow, toCol)
        getChessBoardFromLocalStorage()
    }

    fun getChessBoardAfterRemove(row: Int, col: Int) {
        boardApi.removePiece(row, col)
        getChessBoardFromLocalStorage()
    }

    fun getNewBoard() {
        boardApi.reset()
        getChessBoardFromLocalStorage()
    }
}