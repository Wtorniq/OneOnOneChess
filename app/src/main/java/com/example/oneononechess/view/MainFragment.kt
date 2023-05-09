package com.example.oneononechess.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.oneononechess.app
import com.example.oneononechess.databinding.FragmentMainBinding
import com.example.oneononechess.model.entity.BoardModel
import com.example.oneononechess.model.entity.ChessPiece

class MainFragment : Fragment(), ChessViewInterface{

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { app.boardViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.chessView.chessViewInterface = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val liveData = viewModel.getLiveData()
        val observer = Observer<BoardModel>{setBoard(it)}
        liveData.observe(viewLifecycleOwner, observer)
        viewModel.getChessBoard()

        binding.resetBtn.setOnClickListener {
            viewModel.getNewBoard()
        }
    }

    private fun setBoard(boardModel : BoardModel){
        binding.chessView.setBoard(boardModel)
    }

    override fun onRegularMove(movingPiece: ChessPiece, toRow: Int, toCol: Int) {
        viewModel.getChessBoardAfterMove(movingPiece, toRow, toCol)
    }

    override fun onRemovePiece(row: Int, col: Int) {
        viewModel.getChessBoardAfterRemove(row, col)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}