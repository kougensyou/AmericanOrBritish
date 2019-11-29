package com.example.americanorbritish

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var inputEmail: EditText
    private lateinit var btnReset: Button
    private lateinit var btnBack: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        inputEmail = findViewById<View>(R.id.email) as EditText
        btnReset = findViewById<View>(R.id.btn_reset_password) as Button
        btnBack = findViewById<View>(R.id.btn_back) as Button
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar

        auth = FirebaseAuth.getInstance()

        btnBack.setOnClickListener { finish() }

        btnReset.setOnClickListener(View.OnClickListener {
            val email = inputEmail.text.toString().trim { it <= ' ' }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(application, "登録してあるメールアドレスを入力して下さい", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }

            progressBar.visibility = View.VISIBLE
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@ResetPasswordActivity,
                            "パスワード変更の手順を記載したメールを送信しました!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@ResetPasswordActivity,
                            "メール送信失敗",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    progressBar.visibility = View.GONE
                }
        })
    }

}