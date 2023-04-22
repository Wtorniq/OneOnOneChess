package com.example.oneononechess.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oneononechess.databinding.FragmentMainBinding
import com.example.oneononechess.model.entity.ChessPiece
import com.example.oneononechess.mvp.MainPresenter
import com.example.oneononechess.mvp.Presenter

class MainFragment : Fragment(), ChessViewInterface, com.example.oneononechess.mvp.View {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var presenter: Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = restorePresenter()
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
        presenter?.onAttach(this)
        presenter?.onInit()
    }

    override fun setBoard(boardModel : ArrayList<ArrayList<ChessPiece?>>){
        binding.chessView.setBoard(boardModel)
    }

    override fun getHandler(): Handler = Handler(Looper.getMainLooper())

    override fun onRegularMove(movingPiece: ChessPiece, toRow: Int, toCol: Int) {
        presenter?.onRegularMove(movingPiece, toRow, toCol)
    }

    override fun onRemovePiece(row: Int, col: Int) {
        presenter?.onRemovePiece(row, col)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun restorePresenter(): MainPresenter {
        val presenter = activity?.lastCustomNonConfigurationInstance as? MainPresenter
        return presenter ?: MainPresenter()
    }


    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}