package com.example.americanorbritish

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.americanorbritish.Adapter.ListAdapter
import com.example.americanorbritish.Model.ListModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*


class ResultActivity : AppCompatActivity() {

    var wrongQuests = ArrayList<String>()
    var answerUS = ArrayList<String>()
    var answerUK = ArrayList<String>()

    private val m_parts = ArrayList<ListModel>()

    lateinit internal var listView: ListView
    lateinit internal var yourScore: TextView
    lateinit internal var totalScore: TextView
    var Score = 0
    lateinit internal var Table: String
    private lateinit var mDatabase: DatabaseReference
    private lateinit var auth: FirebaseAuth

    //internal var quizActivity = QuizActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        mDatabase = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        listView = findViewById<ListView>(R.id.listView1)
        yourScore = findViewById<View>(R.id.YourScore) as TextView
        totalScore = findViewById<View>(R.id.TotalScore) as TextView

        val b = intent.extras

        Score = b!!.getInt("score")
        Table = b.getString("section") as String

        yourScore.text = "スコア：" + Score
        totalScore.text = "総スコア：10"


        wrongQuests = intent.getSerializableExtra("wrongQuestions") as ArrayList<String>
        answerUS = intent.getSerializableExtra("answerUS") as ArrayList<String>
        answerUK = intent.getSerializableExtra("answerUK") as ArrayList<String>

        var strQstn = arrayOfNulls<String>(wrongQuests.size)

        for (i in strQstn.indices) {
            m_parts.add(ListModel(wrongQuests.get(i), answerUS.get(i), answerUK.get(i)))
        }

        val listAdapter = ListAdapter(this, R.layout.list_row, m_parts)
        listView.adapter = listAdapter


        mDatabase.child("Scores").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {

                var snapshot = datasnapshot.child(auth.uid!!).child(Table).getValue()

                if (snapshot == null) {
                    mDatabase.child("Scores").child(auth.uid!!).child(Table).setValue(Score)
                } else {
                    var historyscore = snapshot.toString().toInt()
                    if (Score > historyscore) {
                        mDatabase.child("Scores").child(auth.uid!!).child(Table).setValue(Score)
                    }

                }
                Log.e("The read success: ", "sucess")

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("The read failed: ", databaseError.message)
            }
        })
    }
}