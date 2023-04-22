package com.example.oneononechess.mvp

import com.example.oneononechess.model.ChessBoardModel
import com.example.oneononechess.model.entity.ChessPiece

class MainPresenter: Presenter {
    private var view: View? = null
    private val model = ChessBoardModel()
    private var boardModel: ArrayList<ArrayList<ChessPiece?>>? = null

    override fun onAttach(view: View) {
        this.view = view
    }

    override fun onInit() {
        Thread {
            boardModel?.let{
                view?.getHandler()?.post {
                    this.view?.setBoard(boardModel!!)
                }
            } ?: kotlin.run {
                boardModel = model.getChessBoard()
                onInit()
            }
        }.start()
    }

    override fun onRegularMove(movingPiece: ChessPiece, toRow: Int, toCol: Int) {
        Thread{
            model.regularMove(movingPiece, toRow, toCol)
            view?.getHandler()?.post {
                view?.setBoard(boardModel!!)
            }
        }.start()
    }

    override fun onRemovePiece(row: Int, col: Int) {
        Thread{
            model.removePiece(row, col)
            boardModel = model.getChessBoard()
            view?.getHandler()?.post{
                view?.setBoard(boardModel!!)
            }
        }.start()
    }
}