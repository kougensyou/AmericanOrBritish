package com.example.americanorbritish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

import com.example.americanorbritish.DBHelper.DBHelper
import com.example.americanorbritish.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class ScoreActivity : AppCompatActivity() {

    //internal var dbHelper = DBHelper(this)
    lateinit internal var foodScore: TextView
    lateinit internal var transportationScore: TextView
    lateinit internal var clothesScore: TextView
    lateinit internal var randomScore: TextView
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        title = "最高スコア"
        //supportActionBar!!.setHomeButtonEnabled(true)
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        var foodSCORE: Int
        var transportationSCORE: Int
        var clothesSCORE: Int
        var randomSCORE: Int

        foodSCORE = 0
        transportationSCORE = 0
        clothesSCORE = 0
        randomSCORE = 0
        //compFundaB = dbHelper.scoreCompFundaB
        //compFundaI = dbHelper.scoreCompFundaI
        //compFundaE = dbHelper.scoreCompFundaE
        foodScore = findViewById<TextView>(R.id.foodscore)
        transportationScore = findViewById<TextView>(R.id.transportationscore)
        clothesScore = findViewById<TextView>(R.id.clothesscore)
        randomScore = findViewById<TextView>(R.id.randomscore)
        if (foodSCORE < 10) {
            foodScore.text = "0$foodSCORE"
        } else {
            foodScore.text = "" + foodSCORE
        }
        if (transportationSCORE < 10) {
            transportationScore.text = "0$transportationSCORE"
        } else {
            transportationScore.text = "" + transportationSCORE
        }
        if (clothesSCORE < 10) {
            clothesScore.text = "0$clothesSCORE"
        } else {
            clothesScore.text = "" + clothesSCORE
        }
        if (randomSCORE < 10) {
            randomScore.text = "0$randomSCORE"
        } else {
            randomScore.text = "" + randomSCORE
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}