package com.example.oneononechess

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.oneononechess.model.ChessBoardApi
import com.example.oneononechess.model.ChessBoardApiImpl
import com.example.oneononechess.view.ChessBoardViewModel

class App : Application() {
    private val api: ChessBoardApi by lazy { ChessBoardApiImpl() }
    val boardViewModel by lazy { ChessBoardViewModel(api) }
}

/*val Context.app: App
get() {
    return applicationContext as App
}*/

val Fragment.app: App
get() = requireActivity().application as App