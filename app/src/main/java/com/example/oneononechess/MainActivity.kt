package com.example.oneononechess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.oneononechess.view.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commit()
    }

    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onRetainCustomNonConfigurationInstance()",
        "androidx.appcompat.app.AppCompatActivity"
    )
    )
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return super.onRetainCustomNonConfigurationInstance()
    }
}