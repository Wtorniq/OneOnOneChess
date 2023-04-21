package com.example.oneononechess.view

import com.example.oneononechess.model.entity.ChessPiece

interface ChessViewInterface {
    fun onRegularMove(movingPiece: ChessPiece, toRow: Int, toCol: Int)
    fun onRemovePiece(row: Int, col: Int)
}