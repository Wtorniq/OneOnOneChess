package com.example.oneononechess.mvp

import android.os.Handler
import androidx.annotation.MainThread
import com.example.oneononechess.model.entity.ChessPiece

interface View {
    @MainThread
    fun setBoard(boardModel : ArrayList<ArrayList<ChessPiece?>>)
    fun getHandler(): Handler
}
interface Presenter {
    fun onAttach(view: View)
    fun onInit()
    fun onRegularMove(movingPiece: ChessPiece, toRow: Int, toCol: Int)
    fun onRemovePiece(row: Int, col: Int)
}