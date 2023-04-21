package com.example.oneononechess.model.entity

data class ChessPiece(
    val col: Int,
    val row: Int,
    val color: ChessPieceColor,
    val rank: ChessPieceRank,
    val resId: Int
)
