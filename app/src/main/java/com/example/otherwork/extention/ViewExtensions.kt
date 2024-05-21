package com.example.otherwork.extention

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.otherwork.R


val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(this.context)

fun EditText.onTextChange(text: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            text(s.toString())
            if (hasFocus()) {
                setBackgroundResource(if (s.isNullOrEmpty()) R.drawable.drawable_corner_edittext else R.drawable.drawable_corner_edittext_has_text)
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun View.setError(textView: TextView, msg: String) {
    this.setBackgroundResource(R.drawable.bg_error)
    textView.text = msg
    textView.isVisible = true
}


fun View.resetError(textView: TextView) {
    this.setBackgroundResource(R.drawable.drawable_corner_edittext)
    textView.isVisible = false
}

fun View.setViewBackground(drawable:Int) {
    this.setBackgroundResource(drawable)
}

fun EditText.TxT(): String {
    return this.text.toString()
}

fun TextView.TxT(): String {
    return this.text.toString()
}

/**
 * change status bar for the screen
 */
@SuppressLint("ObsoleteSdkInt")
fun Context.setStatusBarColor(statusBarColor: Int, iconStatusBarColor: Int) {
    val window = when (this) {
        is Activity -> this.window
        is Fragment -> this.activity?.window
        else -> null
    }

    window?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            it.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            it.statusBarColor = ContextCompat.getColor(this, statusBarColor)

            // Set status bar icon color
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val decorView = it.decorView
                var flags = decorView.systemUiVisibility
                flags = if (iconStatusBarColor == R.color.white) {
                    flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                } else {
                    flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                decorView.systemUiVisibility = flags
            }
        }
    }
}


