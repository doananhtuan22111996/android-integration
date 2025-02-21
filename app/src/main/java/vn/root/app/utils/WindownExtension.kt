package vn.root.app.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun Window.hideSoftKeyboard() {
    val inputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
}

@SuppressLint("ClickableViewAccessibility")
fun Window.setupViewClickHideKeyBoard(vararg views: View) {
    for (view in views) {
        if (view is EditText || view.isClickable) {
            continue
        }
        view.setOnTouchListener { _, _ ->
            if (currentFocus != null && currentFocus!!.windowToken != null) {
                hideSoftKeyboard()
                currentFocus!!.clearFocus()
            }
            false
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupViewClickHideKeyBoard(innerView)
            }
        }
    }
}
