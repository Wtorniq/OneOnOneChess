package com.example.oneononechess.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.oneononechess.model.entity.BoardModel
import com.example.oneononechess.model.entity.ChessPiece

class ChessView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    var chessViewInterface : ChessViewInterface? = null
    private val displayMetrics = resources.displayMetrics
    private val screenWidth = displayMetrics.widthPixels
    private val boardPaint = Paint()
    private var squareSize = 0
    private var boardSize = 0
    private var boardLeft = 0
    private var boardTop = 0
    private var board = arrayListOf(arrayListOf<ChessPiece?>())
    private var fromRow = -1
    private var fromCol = -1
    private var movingY = -1f
    private var movingX = -1f
    private var movingPiece: ChessPiece? = null

    fun setBoard(addedBoard: BoardModel){
        board = addedBoard.board
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredHeightSpec = MeasureSpec.makeMeasureSpec(screenWidth, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, desiredHeightSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        squareSize = width / 8
        boardSize = squareSize * 8

        boardLeft = (width - boardSize) / 2
        boardTop = (height - boardSize) / 4
        buildChessBoard(canvas)
        drawPieces(canvas)
    }

    private fun drawPieces(canvas: Canvas){
        board.forEach { row ->
            row.forEach { piece ->
                piece?.let {
                    drawPieceAt(canvas, piece.col, piece.row, piece.resId)
                }
            }
        }
        setOnTouchListener { v, event ->
            v.performClick()
            event ?: return@setOnTouchListener false
            when (event.action){
                MotionEvent.ACTION_DOWN -> {
                    fromCol = ((event.x - boardLeft) / squareSize).toInt()
                    fromRow = ((event.y - boardTop) / squareSize).toInt()
                    try {
                        movingPiece = board[7 - fromRow][fromCol]
                        chessViewInterface?.onRemovePiece(fromRow, fromCol)
                    } catch (e: Throwable){
                        return@setOnTouchListener false
                    }
                    movingX = event.x
                    movingY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    movingY = event.y
                    movingX = event.x - squareSize / 2
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    val toCol = ((event.x - boardLeft) / squareSize).toInt()
                    val toRow = ((event.y - boardTop) / squareSize).toInt()
                    movingPiece ?: return@setOnTouchListener false
                    chessViewInterface?.onRegularMove(movingPiece!!, toRow, toCol)
                    movingPiece = null
                }
            }
            return@setOnTouchListener true
        }
        movingPiece?.let {
            canvas.drawBitmap(
                BitmapFactory.decodeResource(resources, it.resId),
                null,
                RectF(movingX, movingY, movingX + squareSize, movingY + squareSize),
                boardPaint
            )
        }
    }

    private fun drawPieceAt(canvas: Canvas, row: Int, col: Int, pieceId: Int) {
        val rect = makeRect(row, col)
        val bitmap = BitmapFactory.decodeResource(resources, pieceId)
        canvas.drawBitmap(bitmap, null, rect, boardPaint)
    }

    private fun buildChessBoard(canvas: Canvas){
        for (row in 0..7) {
            for (col in 0 ..7) {
                if ((row + col) % 2 == 0) {
                    boardPaint.color = Color.LTGRAY
                } else {
                    boardPaint.color = Color.DKGRAY
                }
                val rect = makeRect(row, col)
                canvas.drawRect(rect, boardPaint)
            }
        }
    }
    private fun makeRect(row: Int, col: Int): Rect {
        val rectLeft = boardLeft + row * squareSize
        val rectTop = boardTop + col * squareSize
        val rectRight = rectLeft + squareSize
        val rectBottom = rectTop + squareSize

        return Rect(rectLeft, rectTop, rectRight, rectBottom)
    }

}