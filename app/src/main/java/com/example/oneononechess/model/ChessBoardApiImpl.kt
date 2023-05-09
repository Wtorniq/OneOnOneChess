package com.example.oneononechess.model

import com.example.oneononechess.R
import com.example.oneononechess.model.entity.BoardModel
import com.example.oneononechess.model.entity.ChessPiece
import com.example.oneononechess.model.entity.ChessPieceColor
import com.example.oneononechess.model.entity.ChessPieceRank

class ChessBoardApiImpl: ChessBoardApi {
    private val piecesBox = mutableSetOf<ChessPiece>()
    private val board = arrayListOf(arrayListOf<ChessPiece?>())
    init {
        reset()
    }

    override fun reset() {
        with(piecesBox){
            removeAll(this)
            for (i in 0..1) {
                add(ChessPiece(0 + (i * 7), 7, ChessPieceColor.WHITE, ChessPieceRank.ROOK, R.drawable.white_rook))
                add(ChessPiece(0 + (i * 7), 0, ChessPieceColor.BLACK, ChessPieceRank.ROOK, R.drawable.black_rook))
                add(ChessPiece(1 + (i * 5), 7, ChessPieceColor.WHITE, ChessPieceRank.KNIGHT, R.drawable.white_knight))
                add(ChessPiece(1 + (i * 5), 0, ChessPieceColor.BLACK, ChessPieceRank.KNIGHT, R.drawable.black_knight))
                add(ChessPiece(2 + (i * 3), 7, ChessPieceColor.WHITE, ChessPieceRank.BISHOP, R.drawable.white_bishop))
                add(ChessPiece(2 + (i * 3), 0, ChessPieceColor.BLACK, ChessPieceRank.BISHOP, R.drawable.black_bishop))
            }
            for (i in 0..7) {
                add(ChessPiece(i, 6, ChessPieceColor.WHITE, ChessPieceRank.PAWN, R.drawable.white_pawn))
                add(ChessPiece(i, 1, ChessPieceColor.BLACK, ChessPieceRank.PAWN, R.drawable.black_pawn))
            }
            add(ChessPiece(3, 7, ChessPieceColor.WHITE, ChessPieceRank.QUEEN, R.drawable.white_queen))
            add(ChessPiece(3, 0, ChessPieceColor.BLACK, ChessPieceRank.QUEEN, R.drawable.black_queen))
            add(ChessPiece(4, 7, ChessPieceColor.WHITE, ChessPieceRank.KING, R.drawable.white_king))
            add(ChessPiece(4, 0, ChessPieceColor.BLACK, ChessPieceRank.KING, R.drawable.black_king))
        }
    }

    private fun pieceAt(row: Int, col: Int): ChessPiece? {
        for (piece in piecesBox){
            if (piece.row == row && piece.col == col) return piece
        }
        return null
    }

    override fun regularMove(movingPiece: ChessPiece, toRow: Int, toCol: Int){
        val capturedPiece = pieceAt(toRow, toCol)
        if (capturedPiece != null){
            if (capturedPiece.color == movingPiece.color) {
                piecesBox.add(movingPiece)
            } else {
                piecesBox.remove(pieceAt(toRow, toCol)) //TODO why????
                piecesBox.add(ChessPiece(toCol, toRow, movingPiece.color, movingPiece.rank, movingPiece.resId))
            }
        } else {
            piecesBox.add(ChessPiece(toCol, toRow, movingPiece.color, movingPiece.rank, movingPiece.resId))
        }
    }

    override fun removePiece(row: Int, col: Int) {
        val movingPiece = pieceAt(row, col) ?: return
        piecesBox.remove(movingPiece)
    }

    override fun getChessBoard(): BoardModel {
        board.clear()
        for (row in 7 downTo 0) {
            val rowArrayList = arrayListOf<ChessPiece?>()
            for (col in 0..7) {
                val piece = pieceAt(row, col)
                if (piece == null) {
                    rowArrayList.add(null)
                } else {
                    rowArrayList.add(piece)
                }
            }
            board.add(rowArrayList)
        }
        return BoardModel(board)
    }

}