package com.example.otherwork.auth

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.example.otherwork.R
import com.example.otherwork.databinding.ActivityLoginBinding
import com.example.otherwork.extention.TxT
import com.example.otherwork.extention.onTextChange
import com.example.otherwork.extention.resetError
import com.example.otherwork.extention.setError
import com.example.otherwork.extention.setStatusBarColor
import com.example.otherwork.home.MainActivity


class ActivityLogin : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor(R.color.color_background, R.color.black)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        initialize()
        addListenerOnView()
        makeUnderLineText()

    }

    private fun initialize() = with(binding) {

        edFullName.onTextChange {
            edFullName.resetError(tvErrorFullName)
        }

        edPassword.onTextChange {
            edPassword.resetError(tvErrorPassword)
        }
    }


    private fun addListenerOnView() {

        binding.btnSignIn.setOnClickListener {
            if (validateForm()){
                //call your api
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun validateForm(): Boolean = with(binding) {
        if (edFullName.TxT().isEmpty()) {
            edFullName.setError(
                tvErrorFullName,
                getString(R.string.please_enter_full_name)
            )
            false
        } else if (edPassword.TxT().isEmpty()) {
            edPassword.setError(tvErrorPassword, getString(R.string.please_enter_your_password))
            false
        } else {
            true
        }
    }

    private fun makeUnderLineText() {
        val spanned = HtmlCompat.fromHtml(getString(R.string.forgot_username_password), HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.tvForgotPassword.text = spanned

        val spannedSignUp = HtmlCompat.fromHtml(getString(R.string.sign_up), HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.tvSignUp.text = spannedSignUp
    }


}