package com.vivek.movieapptask.base

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer


abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutId: Int
    abstract val viewModel: BaseViewModel
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        viewModel.error.observe(this, Observer<Error> {
            if (toast == null) {
                toast = Toast.makeText(this, it.message, Toast.LENGTH_SHORT)
                toast?.show()
            } else {
                toast?.setText(it.message)
                toast?.show()
            }
        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.let {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

}