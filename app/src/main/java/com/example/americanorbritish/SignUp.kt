package com.example.americanorbritish

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var btnSignIn: Button
    private lateinit var btnSignUp: Button
    private lateinit var btnResetPassword: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance()

        btnSignIn = findViewById<View>(R.id.sign_in_button) as Button
        btnSignUp = findViewById<View>(R.id.sign_up_button) as Button
        inputEmail = findViewById<View>(R.id.email) as EditText
        inputPassword = findViewById<View>(R.id.password) as EditText
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        btnResetPassword = findViewById<View>(R.id.btn_reset_password) as Button


        btnResetPassword.setOnClickListener {
            startActivity(
                Intent(
                    this@SignUp,
                    ResetPasswordActivity::class.java
                )
            )
        }

        btnSignIn.setOnClickListener { finish() }

        btnSignUp.setOnClickListener(View.OnClickListener {
            val email = inputEmail.text.toString().trim { it <= ' ' }
            val password = inputPassword.text.toString().trim { it <= ' ' }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(
                    applicationContext,
                    "Password too short, enter minimum 6 characters!",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }

            progressBar.visibility = View.VISIBLE
            //create user
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@SignUp) { task ->
                    Toast.makeText(
                        this@SignUp,
                        "createUserWithEmail:onComplete:" + task.isSuccessful,
                        Toast.LENGTH_SHORT
                    ).show()
                    progressBar.visibility = View.GONE
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful) {
                        Toast.makeText(
                            this@SignUp, "Authentication failed." + task.exception!!,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        auth.currentUser!!.sendEmailVerification()
                        Toast.makeText(this@SignUp, R.string.email_sent, Toast.LENGTH_SHORT).show()
                    }
                }
        })
    }

    override fun onResume() {
        super.onResume()
        progressBar.visibility = View.GONE
    }
}