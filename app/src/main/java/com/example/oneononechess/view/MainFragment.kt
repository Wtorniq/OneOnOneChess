package com.example.oneononechess.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oneononechess.R
import com.example.oneononechess.databinding.FragmentMainBinding
import com.example.oneononechess.model.entity.ChessPiece

class MainFragment : Fragment(), ChessViewInterface {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

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

    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onRegularMove(movingPiece: ChessPiece, toRow: Int, toCol: Int) {
        TODO("Not yet implemented")
    }

    override fun onRemovePiece(row: Int, col: Int) {
        TODO("Not yet implemented")
    }
}