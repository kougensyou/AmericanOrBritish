package com.example.americanorbritish

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import android.widget.LinearLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var database: FirebaseDatabase? = null
    private var auth: FirebaseAuth? = null
    private var myRef: DatabaseReference? = null
    private var isAdmin = false

    lateinit internal var food: LinearLayout
    lateinit internal var transportation: LinearLayout
    lateinit internal var clothes: LinearLayout
    lateinit internal var random: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        auth = FirebaseAuth.getInstance()
        //database = FirebaseDatabase.getInstance()
        //myRef = database!!.reference

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        //setUsersDatabase();
        food = findViewById<View>(R.id.food) as LinearLayout
        transportation = findViewById<View>(R.id.transportation) as LinearLayout
        clothes = findViewById<View>(R.id.clothes) as LinearLayout
        random = findViewById<View>(R.id.random) as LinearLayout

        food?.setOnClickListener {
            val i = Intent(applicationContext, QuizActivity::class.java)
            i.putExtra("table_name", "quizFood")

            startActivity(i)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
        transportation?.setOnClickListener {
            val i = Intent(applicationContext, QuizActivity::class.java)
            i.putExtra("table_name", "quizTransportation")
            startActivity(i)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
        clothes?.setOnClickListener {
            val i = Intent(applicationContext, QuizActivity::class.java)
            i.putExtra("table_name", "quizClothes")
            startActivity(i)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
        random?.setOnClickListener {
            val i = Intent(applicationContext, QuizActivity::class.java)
            i.putExtra("table_name", "quizRamdom")
            startActivity(i)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_quiz) {
            startActivity(Intent(this@MainActivity, MainActivity::class.java))
        } else if (id == R.id.nav_score) {
            startActivity(Intent(this@MainActivity, ResultActivity::class.java))
        } else if (id == R.id.nav_respass) {
            startActivity(Intent(this@MainActivity, ResetPasswordActivity::class.java))
        } else if (id == R.id.nav_signout) {
            auth!!.signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}
