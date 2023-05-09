package com.example.oneononechess.model

import com.example.oneononechess.model.entity.BoardModel
import com.example.oneononechess.model.entity.ChessPiece

interface ChessBoardApi {
    fun reset()
    fun getChessBoard(): BoardModel
    fun regularMove(movingPiece: ChessPiece, toRow: Int, toCol: Int)
    fun removePiece(row: Int, col: Int)
}