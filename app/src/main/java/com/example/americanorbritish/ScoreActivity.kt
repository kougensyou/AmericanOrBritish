package com.example.americanorbritish

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ScoreActivity : AppCompatActivity() {

    //internal var dbHelper = DBHelper(this)
    lateinit internal var foodScore: TextView
    lateinit internal var transportationScore: TextView
    lateinit internal var clothesScore: TextView
    lateinit internal var randomScore: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        mDatabase = database!!.reference

        title = "最高スコア"
        //supportActionBar!!.setHomeButtonEnabled(true)
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        foodScore = findViewById<TextView>(R.id.foodscore)
        transportationScore = findViewById<TextView>(R.id.transportationscore)
        clothesScore = findViewById<TextView>(R.id.clothesscore)
        randomScore = findViewById<TextView>(R.id.randomscore)

        mDatabase.child("Scores").addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var quizFoodScore = dataSnapshot.child(auth.uid!!).child("quizFood").getValue()
                var quizTransportationScore =
                    dataSnapshot.child(auth.uid!!).child("quizTransportation").getValue()
                var quizClothesScore =
                    dataSnapshot.child(auth.uid!!).child("quizClothes").getValue()
                var quizRandomScore =
                    dataSnapshot.child(auth.uid!!).child("quizRandom").getValue()


                if (quizFoodScore != null) {
                    foodScore.text = quizFoodScore.toString()
                } else {
                    foodScore.text = "0"
                }
                if (quizTransportationScore != null) {
                    transportationScore.text = quizTransportationScore.toString()
                } else {
                    transportationScore.text = "0"
                }
                if (quizClothesScore != null) {
                    clothesScore.text = quizClothesScore.toString()
                } else {
                    clothesScore.text = "0"
                }
                if (quizRandomScore != null) {
                    randomScore.text = quizRandomScore.toString()
                } else {
                    randomScore.text = "0"
                }

                Log.e("The read success: ", "sucess")

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("The read failed: ", databaseError.message)
            }

        })


    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}